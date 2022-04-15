package com.example.dddorder.interfaces.partner;

import com.example.dddorder.application.partner.PartnerFacade;
import com.example.dddorder.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerApiController {

    private final PartnerFacade partnerFacade;

    // 등록
    @PostMapping()
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest request) {
        log.debug("request={}", request.toString());
        // 1. 호출 파라미터 -> Command 로 변환, Criteria convert
        // 2. facade 호출 PartnerInfo
        // 3. PartnerInfo -> CommonResponse convert And return
        var command = request.toCommand(); // 계층별 분리를 위해서 변환
        var partnerInfo = partnerFacade.registerPartner(command);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    // 조회
    @GetMapping(value = "getPartnerInfo")
    public CommonResponse getPartnerInfo(String partnerToken) {
        var partnerInfo = partnerFacade.getPartnerInfo(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    // 활성화
    @PostMapping(value = "/enablePartner/{partnerToken}")
    public CommonResponse enablePartner(@PathVariable("partnerToken") String partnerToken) {
        var partnerInfo = partnerFacade.enablePartner(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    // 비활성화
    @PostMapping(value = "/disablePartner/{partnerToken}")
    public CommonResponse disablePartner(@PathVariable("partnerToken") String partnerToken) {
        var partnerInfo = partnerFacade.disablePartner(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }
}
