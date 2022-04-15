package com.example.dddorder.domain.item;

import com.example.dddorder.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends BaseEntity {

    private static final String ITEM_PREFIX = "item_";

    private Long id;
    private String itemToken;
}
