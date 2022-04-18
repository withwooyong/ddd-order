package com.example.dddorder.domain.order;

public interface OrderReader {
    Order getOrder(String orderToken);
}
