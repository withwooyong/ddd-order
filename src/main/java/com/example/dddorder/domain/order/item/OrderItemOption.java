package com.example.dddorder.domain.order.item;

import com.example.dddorder.common.exception.InvalidParamException;
import com.example.dddorder.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_item_options")
public class OrderItemOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_option_group_id")
    private OrderItemOptionGroup orderItemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public OrderItemOption(OrderItemOptionGroup orderItemOptionGroup, Integer ordering, String itemOptionName, Long itemOptionPrice) {
        if (orderItemOptionGroup == null) throw new InvalidParamException();
        if (ordering == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(itemOptionName)) throw new InvalidParamException();
        if (itemOptionPrice == null) throw new InvalidParamException();

        this.orderItemOptionGroup = orderItemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
