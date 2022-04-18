package com.example.dddorder.infrastructure.order.payment;

import com.example.dddorder.domain.order.OrderCommand;
import com.example.dddorder.domain.order.payment.PayMethod;

public class KakaoPayApiCallerImpl implements PaymentApiCaller {
    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.KAKAO_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - Kakao
    }
}
