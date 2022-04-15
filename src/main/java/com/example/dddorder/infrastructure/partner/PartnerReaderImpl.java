package com.example.dddorder.infrastructure.partner;

import com.example.dddorder.common.exception.EntityNotFoundException;
import com.example.dddorder.domain.partner.Partner;
import com.example.dddorder.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReaderImpl implements PartnerReader {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(Long partnerId) {
        log.debug("partnerId={}", partnerId);
        return partnerRepository.findById(partnerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Partner getPartner(String partherToken) {
        log.debug("partherToken={}", partherToken);
        return partnerRepository.findByPartnerToken(partherToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
