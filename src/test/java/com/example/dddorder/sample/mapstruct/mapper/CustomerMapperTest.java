package com.example.dddorder.sample.mapstruct.mapper;

import com.example.dddorder.sample.mapstruct.dto.Customer;
import com.example.dddorder.sample.mapstruct.dto.CustomerDto;
import com.example.dddorder.sample.mapstruct.dto.OrderItem;
import com.example.dddorder.sample.mapstruct.dto.OrderItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CustomerMapperTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void toCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.id = 10L;
        customerDto.customerName = "Filip";
        OrderItemDto order1 = new OrderItemDto();
        order1.name = "Table";
        order1.quantity = 2L;
        customerDto.orders = new ArrayList<>(Collections.singleton(order1));

        Customer customer = CustomerMapper.MAPPER.toCustomer(customerDto);
        System.out.println(customer);

        assertThat(customer.getId()).isEqualTo(10);
        assertThat(customer.getName()).isEqualTo("Filip");
        assertThat(customer.getOrderItems())
                .extracting("name", "quantity")
                .containsExactly(tuple("Table", 2L));
    }

    @Test
    void fromCustomer() {
        Customer customer = new Customer();
        customer.setId(10L);
        customer.setName("Filip");
        OrderItem order1 = new OrderItem();
        order1.setName("Table");
        order1.setQuantity(2L);
        customer.setOrderItems(Collections.singleton(order1));

        CustomerDto customerDto = CustomerMapper.MAPPER.fromCustomer(customer);

        assertThat(customerDto.id).isEqualTo(10);
        assertThat(customerDto.customerName).isEqualTo("Filip");
        assertThat(Collections.singletonList(customerDto.orders))
                .extracting("name", "quantity")
                .containsExactly(tuple("Table", 2L));
    }


    @Test
    void testTest() {
        System.out.println("===");
    }

    @Test
    public void testMapDtoToEntity() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.id = 10L;
        customerDto.customerName = "Filip";
        OrderItemDto order1 = new OrderItemDto();
        order1.name = "Table";
        order1.quantity = 2L;
        customerDto.orders = new ArrayList<>(Collections.singleton(order1));

        Customer customer = CustomerMapper.MAPPER.toCustomer(customerDto);
        System.out.println(customer);

        assertThat(customer.getId()).isEqualTo(10);
        assertThat(customer.getName()).isEqualTo("Filip");
        assertThat(customer.getOrderItems())
                .extracting("name", "quantity")
                .containsExactly(tuple("Table", 2L));
    }

    @Test
    public void testEntityDtoToDto() {

        Customer customer = new Customer();
        customer.setId(10L);
        customer.setName("Filip");
        OrderItem order1 = new OrderItem();
        order1.setName("Table");
        order1.setQuantity(2L);
        customer.setOrderItems(Collections.singleton(order1));

        CustomerDto customerDto = CustomerMapper.MAPPER.fromCustomer(customer);

        assertThat(customerDto.id).isEqualTo(10);
        assertThat(customerDto.customerName).isEqualTo("Filip");
        assertThat(Collections.singletonList(customerDto.orders))
                .extracting("name", "quantity")
                .containsExactly(tuple("Table", 2L));
    }
}