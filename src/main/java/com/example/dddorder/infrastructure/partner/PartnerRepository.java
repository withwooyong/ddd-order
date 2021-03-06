package com.example.dddorder.infrastructure.partner;

import com.example.dddorder.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByPartnerToken(String partnerToken);

    Optional<Partner> findByPartnerName(String partnerName);
}
