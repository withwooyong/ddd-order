package com.example.dddorder.sample.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {

    private String name;
    private Long quantity;
}