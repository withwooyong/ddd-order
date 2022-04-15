package com.example.dddorder.application.item;

import com.example.dddorder.domain.item.ItemCommand;
import com.example.dddorder.domain.item.ItemInfo;
import com.example.dddorder.domain.item.ItemService;
import com.example.dddorder.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemFacade {

    private final ItemService itemService;
    private final NotificationService notificationService;

    // 등록
    public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken) {
        var itemToken = itemService.registerItem(request, partnerToken);
        notificationService.sendKakao("010-1234-5678", "description");
        return itemToken;
    }

    public void changeOnSaleItem(String itemToken) {
        itemService.changeOnSaleItem(itemToken);
    }

    public void changeEndOfSaleItem(String itemToken) {
        itemService.changeEndOfSaleItem(itemToken);
    }

    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        return itemService.retrieveItemInfo(itemToken);
    }
}
