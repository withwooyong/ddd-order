package com.example.dddorder.domain.item;

import com.example.dddorder.common.exception.InvalidParamException;
import com.example.dddorder.common.util.TokenGenerator;
import com.example.dddorder.domain.BaseEntity;
import com.example.dddorder.domain.item.optiongroup.ItemOptionGroup;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

// Factory 
// Item Aggregation
// <root> item -> optionGroup -> option
@Slf4j
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends BaseEntity {

    private static final String ITEM_PREFIX = "item_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemToken;
    private Long partnerId;
    private String itemName;
    private Long itemPrice;

    // item : itemOptionGroup = 1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;


    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매종료");
        private final String description;
    }

    public void changeOnSale() {
        this.status = Status.ON_SALE;
    }

    public void changeEndOfSale() {
        this.status = Status.END_OF_SALE;
    }

    public boolean availableSales() {
        return this.status == Status.ON_SALE;
    }

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice) {
        if (partnerId == null) throw new InvalidParamException("Item.partnerId");
        if (StringUtils.isBlank(itemName)) throw new InvalidParamException("Item.itemName");
        if (itemPrice == null) throw new InvalidParamException("Item.itemPrice");

        this.partnerId = partnerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX);
        this.status = Status.PREPARE;
    }
}
