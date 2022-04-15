package com.example.dddorder.domain.item;

public interface ItemService {
    String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken);

    void changeOnSaleItem(String itemToken);

    void changeEndOfSaleItem(String itemToken);

    ItemInfo.Main retrieveItemInfo(String itemToken);
}
