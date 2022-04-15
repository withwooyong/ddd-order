package com.example.dddorder.infrastructure.partner;

import com.example.dddorder.domain.partner.Partner;
import com.example.dddorder.domain.partner.PartnerReader;
import com.example.dddorder.domain.partner.PartnerStore;
import org.springframework.jdbc.core.JdbcTemplate;

// JPA 에서 JDBC 로 변경할려면 이렇게 할 수 있다.
// Service class 에서 @Resource(name = "PartnerJdbcTemplateImpl") or @Qualifier annotation 을 사용하여 변경할 수 있다.
public class PartnerJdbcTemplateImpl implements PartnerReader, PartnerStore {

    @Override
    public Partner getPartner(Long partnerId) {
//        JdbcTemplate jdbcTemplate
        return null;
    }

    @Override
    public Partner getPartner(String partherToken) {
        return null;
    }

    @Override
    public Partner store(Partner partner) {
        return null;
    }
}
