package com.example.dddorder.infrastructure.item.option;

import com.example.dddorder.domain.item.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
