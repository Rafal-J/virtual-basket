package com.pragmatic.basket.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BasketDao extends CrudRepository<Basket, Integer> {

    @Query(nativeQuery = true)
    Basket findOpenBasket(@Param("USER_ID") int user_id);

}