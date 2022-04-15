package com.example.dddorder.domain.partner;

public interface PartnerReader {

    Partner getPartner(Long partnerId);

    Partner getPartner(String partherToken);

}
