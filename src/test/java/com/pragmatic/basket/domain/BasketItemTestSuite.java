package com.pragmatic.basket.domain;

import com.pragmatic.basket.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketItemTestSuite {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    BasketItemDao basketItemDao;

    @Autowired
    DbService dbService;


    @Test
    public void testAddingNewItemsAndBasket() {
        //Given
        Item item1 = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        Item item2 = new Item("Kontakt", new BigDecimal("22.00"), 8, new BigDecimal("20.50"));
        Basket basket = new Basket(12, true);

        //When
        itemDao.save(item1);
        itemDao.save(item2);
        basketDao.save(basket);
        dbService.addItemToBasket(12, item1.getId(), 10);


        //Then


        //CleanUp
        basketItemDao.deleteAll();
        itemDao.deleteAll();
        basketDao.deleteAll();

    }

}