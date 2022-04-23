package com.example.dddorder.sample.mapstruct.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerDto {

    public Long id;
    public String customerName;
    public List<OrderItemDto> orders;
}

