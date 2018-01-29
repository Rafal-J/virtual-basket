package com.pragmatic.basket.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BasketItemDao extends CrudRepository <BasketItem, Integer> {
    List<BasketItem> findBasketItemsByBasketId(int id);
    BasketItem findBasketItemByBasketIdAndItemId(int basketId, int itemId);
    void deleteBasketItemByBasketIdAndItemId(int basketId, int itemId);
}
