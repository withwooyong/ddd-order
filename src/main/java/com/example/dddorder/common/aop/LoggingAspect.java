package com.example.dddorder.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass()); // logger.debug 안 찍힘

    // controller 패키지에서 *Controller 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    // service 패키지에서 *Service 클래스에 있는 메서드 중 파라미터 0개인 메서드만
//    @Around("execution(* com.map..controller.*Controller.*()) || execution(* com.map..service.*Service.*())")
    @Around("execution(* com.example.dddorder..*()) )")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("This - '" + joinPoint.getThis() + "'"); // Advice를 행하는 객체
        log.info("Kind - '" + joinPoint.getKind() + "'"); // 해당 Advice 의 타입
        log.error("Target - '" + joinPoint.getTarget() + "'"); // Target 객체

        String type = "test";
        String name = joinPoint.getSignature().getDeclaringTypeName();
        // getSignature() : 실행되는 대상 객체의 메서드에 대한 정보를 가지고 옴

//        if (name.contains("Controller")) {
//            type = "Controller - '";
//
//        } else if (name.contains("Service")) {
//            type = "Service - '";
//        }

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()'");
        // getName - 메서드 이름
        return joinPoint.proceed();
    }
}
