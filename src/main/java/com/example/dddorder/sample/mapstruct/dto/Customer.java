package com.example.dddorder.sample.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString
public class Customer {

    private Long id;
    private String name;
    private Collection<OrderItem> orderItems;
}
