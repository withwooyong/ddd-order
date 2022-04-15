package com.example.dddorder.domain.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;
// 서비스는 가독성 있게
// 최대한 가볍게 가져가고
// 복잡한 로직은 infrastructure 에서 구현한다.
@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerStore partnerStore;    // 저장 interface 구현(Implementation)은 infratructure에서 함.
    private final PartnerReader partnerReader;  // 조회 interface 구현(Implementation)은 infratructure에서 함.

    // 등록
    @Override
    @Transactional
    public PartnerInfo registerPartner(PartnerCommand command) {
        // 1. command -> initPartner
        // 2. initPartner save to DB
        // 3. Partner -> PartnerInfo And Return
        var initPartner = command.toEntity(); // command 를 엔티티로 변환
        Partner partner = partnerStore.store(initPartner); // 저장
        return new PartnerInfo(partner);    // PartnerInfo로 변환 후 리턴
    }

    // 조회
    @Override
    @Transactional(readOnly = true)
    public PartnerInfo getPartnerInfo(String partnerToken) {
        // 1. partnerToken -> Partner
        // 2. Partner -> PartnerInfo And return
        Partner partner = partnerReader.getPartner(partnerToken);
        return new PartnerInfo(partner);
    }

    // 저장 (활성화)
    @Override
    @Transactional
    public PartnerInfo enablePartner(String partnerToken) {
        // 1. partnerToken -> Partner
        // 2. partner.enable()
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.enable();
        return new PartnerInfo(partner);
    }

    // 저장 (비활성화)
    @Override
    @Transactional
    public PartnerInfo disablePartner(String partnerToken) {
        // 1. partnerToken -> Partner
        // 2. partner.disable()
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.disable();
        return new PartnerInfo(partner);
    }
}
