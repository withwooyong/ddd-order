package com.example.dddorder.domain.order;

import com.example.dddorder.domain.order.item.OrderItem;

import java.util.List;

public interface OrderItemSeriesFactory {
    List<OrderItem> store(Order order, OrderCommand.RegisterOrder requestOrder);
}
