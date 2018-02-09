package com.pragmatic.basket.domain.dao;

import com.pragmatic.basket.domain.Basket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface BasketDao extends CrudRepository<Basket, Integer> {
    @Query
    Basket findBasketByUserIdAndOpen(int  userId, boolean open);
}