package com.example.dddorder.domain.partner;

import com.example.dddorder.common.exception.InvalidParamException;
import com.example.dddorder.common.util.TokenGenerator;
import com.example.dddorder.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "partners")
public class Partner extends BaseEntity {

    private static final String PREFIX_PARTNER = "ptn_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String partnerToken;

    @Column(nullable = false)
    private String partnerName;

    @Column(nullable = false)
    private String businessNo;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;
    // endtity end

    ///////////////////////////////////////////////////////////////
    // entity enum

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");
        private final String description;
    }
    ///////////////////////////////////////////////////////////////
    // entity function

    // 필수값
    @Builder
    public Partner(String partnerName, String businessNo, String email) {
        // validation check
        if (StringUtils.isEmpty(partnerName)) throw new InvalidParamException("empty partnerName");
        if (StringUtils.isEmpty(businessNo)) throw new InvalidParamException("empty businessNo");
        if (StringUtils.isEmpty(email)) throw new InvalidParamException("empty email");

        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);  // 기본값 생성
        this.status = Status.ENABLE;  // 기본값 설정
    }

    // service 에서 처리하지 않고 entity 에서 처리한다.
    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }


}
