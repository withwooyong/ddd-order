package com.example.dddorder.domain.order;

import com.example.dddorder.common.exception.IllegalStatusException;
import com.example.dddorder.common.exception.InvalidParamException;
import com.example.dddorder.common.util.TokenGenerator;
import com.example.dddorder.domain.BaseEntity;
import com.example.dddorder.domain.order.fragment.DeliveryFragment;
import com.example.dddorder.domain.order.item.OrderItem;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    private static final String ORDER_PREFIX = "ord_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderToken;
    private Long userId;
    private String payMethod;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItemList = Lists.newArrayList();

    @Embedded
    private DeliveryFragment deliveryFragment;

    private ZonedDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @Builder
    public Order(Long userId, String payMethod, DeliveryFragment deliveryFragment) {
        if (userId == null) throw new InvalidParamException("Order.userId");
        if (StringUtils.isEmpty(payMethod)) throw new InvalidParamException("Order.payMethod");
        if (deliveryFragment == null) throw new InvalidParamException("Order.deliveryFragment");

        this.orderToken = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX);
        this.userId = userId;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.orderedAt = ZonedDateTime.now();
        this.status = Status.INIT;
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품의 가격 = (상품 가격 + 상품 옵션 가격) * 주문 갯수
     */
    public Long calculateTotalAmount() {
        return orderItemList.stream()
                .mapToLong(OrderItem::calculateTotalAmount)
                .sum();
    }

    public void orderComplete() {
        if (this.status != Status.INIT) throw new IllegalStatusException();
        this.status = Status.ORDER_COMPLETE;
    }

    public boolean isAlreadyPaymentComplete() {
        switch (this.status) {
            case ORDER_COMPLETE:
            case DELIVERY_PREPARE:
            case IN_DELIVERY:
            case DELIVERY_COMPLETE:
                return true;
        }
        return false;
    }
}
