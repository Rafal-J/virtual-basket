package com.pragmatic.basket.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface BasketDao extends CrudRepository<Basket, Integer> {
    @Query
    Basket findBasketByUserIdAndOpen(int  userId, boolean open);
}