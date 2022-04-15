package com.example.dddorder.domain.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendEmail(String email, String title, String description) {
        log.info("email={}, title={}, description={}", email, title, description);
    }

    @Override
    public void sendKakao(String phoneNo, String description) {
        log.info("phoneNo={}, description={}", phoneNo, description);
    }

    @Override
    public void sendSms(String phoneNo, String description) {
        log.info("phoneNo={}, description={}", phoneNo, description);
    }
}
