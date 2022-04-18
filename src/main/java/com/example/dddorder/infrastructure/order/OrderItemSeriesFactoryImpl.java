package com.example.dddorder.infrastructure.order;

import com.example.dddorder.domain.item.ItemReader;
import com.example.dddorder.domain.order.Order;
import com.example.dddorder.domain.order.OrderCommand;
import com.example.dddorder.domain.order.OrderItemSeriesFactory;
import com.example.dddorder.domain.order.OrderStore;
import com.example.dddorder.domain.order.item.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {
    private final ItemReader itemReader;
    private final OrderStore orderStore;

    @Override
    public List<OrderItem> store(Order order, OrderCommand.RegisterOrder requestOrder) {
        log.debug("requestOrder={}", requestOrder);

        return requestOrder.getOrderItemList().stream()
                .map(orderItemRequest -> {
                    var item = itemReader.getItemBy(orderItemRequest.getItemToken());
                    var initOrderItem = orderItemRequest.toEntity(order, item);
                    var orderItem = orderStore.store(initOrderItem);

                    orderItemRequest.getOrderItemOptionGroupList().forEach(orderItemOptionGroupRequest -> {
                        var initOrderItemOptionGroup = orderItemOptionGroupRequest.toEntity(orderItem);
                        var orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                        orderItemOptionGroupRequest.getOrderItemOptionList().forEach(orderItemOptionRequest -> {
                            var initOrderItemOption = orderItemOptionRequest.toEntity(orderItemOptionGroup);
                            orderStore.store(initOrderItemOption);
                        });
                    });
                    return orderItem;
                }).collect(Collectors.toList());
    }
}