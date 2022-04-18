package com.example.dddorder.domain.order.payment.validator;

import com.example.dddorder.common.exception.InvalidParamException;
import com.example.dddorder.domain.order.Order;
import com.example.dddorder.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator {

    @Override
    public void validate(Order order, OrderCommand.PaymentRequest paymentRequest) {
        if (!order.calculateTotalAmount().equals(paymentRequest.getAmount()))
            throw new InvalidParamException("주문가격이 불일치합니다");
    }
}
