package com.example.dddorder.infrastructure.item;

import com.example.dddorder.domain.item.Item;
import com.example.dddorder.domain.item.ItemCommand;
import com.example.dddorder.domain.item.ItemOptionSeriesFactory;
import com.example.dddorder.domain.item.option.ItemOptionStore;
import com.example.dddorder.domain.item.optiongroup.ItemOptionGroup;
import com.example.dddorder.domain.item.optiongroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item) {
        var itemOptionGroupRequestList = request.getItemOptionGroupRequestList();

        if (CollectionUtils.isEmpty(itemOptionGroupRequestList)) {
            return Collections.emptyList();
        }

        return itemOptionGroupRequestList.stream()
                .map(requestItemOptionGroup -> {
                    var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                    var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup); // itemOptionGroup store

                    requestItemOptionGroup.getItemOptionRequestList().forEach(requestItemOption -> {
                        var initItemOption = requestItemOption.toEntity(itemOptionGroup);
                        itemOptionStore.store(initItemOption);  // itemOption store
                    });
                    return itemOptionGroup;
                }).collect(Collectors.toList());
    }
}
