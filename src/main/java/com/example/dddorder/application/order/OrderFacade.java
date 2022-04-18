package com.example.dddorder.application.order;

import com.example.dddorder.domain.notification.NotificationService;
import com.example.dddorder.domain.order.OrderCommand;
import com.example.dddorder.domain.order.OrderInfo;
import com.example.dddorder.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final NotificationService notificationService;

    public String registerOrder(OrderCommand.RegisterOrder registerOrder) {
        log.debug("registerOrder={}", registerOrder.toString());
        var orderToken = orderService.registerOrder(registerOrder);
        notificationService.sendKakao("ORDER_COMPLETE", "content");
        return orderToken;
    }

    public OrderInfo.Main retrieveOrder(String orderToken) {
        return orderService.retrieveOrder(orderToken);
    }

    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        orderService.paymentOrder(paymentRequest);
        notificationService.sendKakao(null, null);
    }
}
