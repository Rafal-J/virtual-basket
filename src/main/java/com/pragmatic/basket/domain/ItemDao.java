package com.pragmatic.basket.domain;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface ItemDao extends CrudRepository<Item, Integer> {

}