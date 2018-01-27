package com.pragmatic.basket.domain;

import org.springframework.data.repository.CrudRepository;

public interface BasketItemDao extends CrudRepository <BasketItem, Integer> {
}
