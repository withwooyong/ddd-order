package com.example.dddorder.infrastructure.order.payment;

import com.example.dddorder.domain.order.OrderCommand;
import com.example.dddorder.domain.order.payment.PayMethod;

public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);

    void pay(OrderCommand.PaymentRequest request);
}
