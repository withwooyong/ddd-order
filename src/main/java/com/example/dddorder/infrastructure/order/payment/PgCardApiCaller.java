package com.example.dddorder.infrastructure.order.payment;

import com.example.dddorder.domain.order.OrderCommand;
import com.example.dddorder.domain.order.payment.PayMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PgCardApiCaller implements PaymentApiCaller {

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.CARD == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - Card
    }
}
