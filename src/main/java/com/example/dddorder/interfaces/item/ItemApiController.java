package com.example.dddorder.interfaces.item;

import com.example.dddorder.application.item.ItemFacade;
import com.example.dddorder.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/items")
public class ItemApiController {

    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;


    @PostMapping
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        log.debug("request={}", request.toString());
        var partnerToken = request.getPartnerToken();
        var itemCommand = itemDtoMapper.of(request);
        log.debug("itemCommand={}", itemCommand.toString());
        var itemToken = itemFacade.registerItem(itemCommand, partnerToken);
        var response = itemDtoMapper.of(itemToken);
        log.debug("response={}", response.toString());
        return CommonResponse.success(response);
    }

    @PostMapping("/change-on-sales")
    public CommonResponse changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        var itemToken = request.getItemToken();
        itemFacade.changeOnSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    @PostMapping("/change-end-of-sales")
    public CommonResponse changeEndOfSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        var itemToken = request.getItemToken();
        itemFacade.changeEndOfSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    @GetMapping("/{itemToken}")
    public CommonResponse retrieveItemInfo(@PathVariable("itemToken") String itemToken) {
        System.out.println("1");
        log.debug("itemToken={}", itemToken);
        var itemInfo = itemFacade.retrieveItemInfo(itemToken);
        var response = itemDtoMapper.of(itemInfo);
        return CommonResponse.success(response);
    }

}
