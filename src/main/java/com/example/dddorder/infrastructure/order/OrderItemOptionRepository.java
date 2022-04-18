package com.example.dddorder.infrastructure.order;

import com.example.dddorder.domain.order.item.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
