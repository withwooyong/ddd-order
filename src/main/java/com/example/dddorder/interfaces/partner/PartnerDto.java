package com.example.dddorder.interfaces.partner;

import com.example.dddorder.domain.partner.Partner;
import com.example.dddorder.domain.partner.PartnerCommand;
import com.example.dddorder.domain.partner.PartnerInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

// PartnerApiController 에서 사용할
// request response Dto
public class PartnerDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {

        @NotEmpty(message = "partnerName 는 필수값입니다")
        private String partnerName;

        @NotEmpty(message = "businessNo 는 필수값입니다")
        private String businessNo;

        @Email(message = "email 형식에 맞아야 합니다")
        @NotEmpty(message = "email 는 필수값입니다")
        private String email;

        // junit Test 하기위해 만들었음.
        public RegisterRequest(@NotEmpty(message = "partnerName 는 필수값입니다") String partnerName,
                               @NotEmpty(message = "businessNo 는 필수값입니다") String businessNo,
                               @Email(message = "email 형식에 맞아야 합니다") @NotEmpty(message = "email 는 필수값입니다") String email) {
            this.partnerName = partnerName;
            this.businessNo = businessNo;
            this.email = email;
        }

        public PartnerCommand toCommand() {
            return PartnerCommand.builder()
                    .partnerName(partnerName)
                    .businessNo(businessNo)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterResponse {
        private final String partnerToken;
        private final String partnerName;
        private final String businessNo;
        private final String email;
        private final Partner.Status status;

        public RegisterResponse(PartnerInfo partnerInfo) {
            this.partnerToken = partnerInfo.getPartnerToken();
            this.partnerName = partnerInfo.getPartnerName();
            this.businessNo = partnerInfo.getBusinessNo();
            this.email = partnerInfo.getEmail();
            this.status = partnerInfo.getStatus();
        }
    }
}
