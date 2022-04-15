package com.example.dddorder.domain.item;

import com.example.dddorder.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemReader itemReader;
    private final ItemStore itemStore;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    @Transactional
    public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken) {
        // 1. get PartnerId
        var partner = partnerReader.getPartner(partnerToken);
        var initItem = request.toEntity(partner.getId());
        var item = itemStore.store(initItem);
        log.debug("item={}", item.toString());
        itemOptionSeriesFactory.store(request, item);
        return item.getItemToken();
    }

    @Override
    @Transactional
    public void changeOnSaleItem(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeOnSale();
    }

    @Override
    @Transactional
    public void changeEndOfSaleItem(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeEndOfSale();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        var itemOptionGroupInfoList = itemReader.getItemOptionSeries(item);
        return new ItemInfo.Main(item, itemOptionGroupInfoList);
    }
}
