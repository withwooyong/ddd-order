package com.example.dddorder.domain.order.payment.validator;

import com.example.dddorder.domain.order.Order;
import com.example.dddorder.domain.order.OrderCommand;

public interface PaymentValidator {
    void validate(Order order, OrderCommand.PaymentRequest paymentRequest);
}
