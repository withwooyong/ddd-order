package com.example.dddorder.domain.order;

import com.example.dddorder.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderItemSeriesFactory orderItemSeriesFactory;
    private final PaymentProcessor paymentProcessor;
    private final OrderInfoMapper orderInfoMapper;

    // 주문등록
    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder registerOrder) {
        log.debug("registerOrder={}", registerOrder.toString());
        Order order = orderStore.store(registerOrder.toEntity());
        orderItemSeriesFactory.store(order, registerOrder);
        return order.getOrderToken();
    }

    // 주문결재
    @Override
    @Transactional
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        var orderToken = paymentRequest.getOrderToken();
        var order = orderReader.getOrder(orderToken);
        paymentProcessor.pay(order, paymentRequest);
        order.orderComplete();
    }

    // 주문확인
    @Override
    @Transactional(readOnly = true)
    public OrderInfo.Main retrieveOrder(String orderToken) {
        var order = orderReader.getOrder(orderToken);
        var orderItemList = order.getOrderItemList();
        return orderInfoMapper.of(order, orderItemList);
    }
}
