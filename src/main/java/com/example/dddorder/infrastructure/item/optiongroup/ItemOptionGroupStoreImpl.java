package com.example.dddorder.infrastructure.item.optiongroup;

import com.example.dddorder.domain.item.optiongroup.ItemOptionGroup;
import com.example.dddorder.domain.item.optiongroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionGroupStoreImpl implements ItemOptionGroupStore {

    private final ItemOptionGroupRepository itemOptionGroupRepository;

    @Override
    public ItemOptionGroup store(ItemOptionGroup itemOptionGroup) {
        log.debug("itemOptionGroup={}", itemOptionGroup.toString());
        return itemOptionGroupRepository.save(itemOptionGroup);
    }
}
