package com.pragmatic.basket.service;

import com.pragmatic.basket.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.xml.transform.sax.SAXSource;
import java.sql.ResultSet;
import java.util.List;

@Transactional
@Repository
public class DbService {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    BasketItemDao basketItemDao;

    public Basket addItemToBasket(int userId, int item, int itemQty) {

        Basket basket = basketDao.findOpenBasket(userId);

         return new Basket();
    }
}
