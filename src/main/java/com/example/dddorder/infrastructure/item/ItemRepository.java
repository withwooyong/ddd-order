package com.example.dddorder.infrastructure.item;

import com.example.dddorder.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemToken(String itemToken);

}
