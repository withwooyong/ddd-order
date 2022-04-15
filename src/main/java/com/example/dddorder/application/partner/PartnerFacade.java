package com.example.dddorder.application.partner;

import com.example.dddorder.domain.notification.NotificationService;
import com.example.dddorder.domain.partner.PartnerCommand;
import com.example.dddorder.domain.partner.PartnerInfo;
import com.example.dddorder.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// 외부 인터페이스를 하나로 통합하기위해 
// Service 윗단의 Facade 계층을 만듦
@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {

    private final PartnerService partnerService;
    private final NotificationService notificationService;

    // 등록
    public PartnerInfo registerPartner(PartnerCommand command) {
        var partnerInfo = partnerService.registerPartner(command);
        notificationService.sendEmail(partnerInfo.getEmail(), "title", "discription");
        return partnerInfo;
    }

    // 조회
    public PartnerInfo getPartnerInfo(String partnerToken) {
        return partnerService.getPartnerInfo(partnerToken);
    }

    // 활성화
    public PartnerInfo enablePartner(String partnerToken) {
        return partnerService.enablePartner(partnerToken);
    }

    // 비활성화
    public PartnerInfo disablePartner(String partnerToken) {
        return partnerService.disablePartner(partnerToken);
    }

}
