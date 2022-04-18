package com.example.dddorder.domain.order.payment;

import com.example.dddorder.domain.order.Order;
import com.example.dddorder.domain.order.OrderCommand;

public interface PaymentProcessor {
    void pay(Order order, OrderCommand.PaymentRequest request);
}
