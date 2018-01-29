package com.pragmatic.basket.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddSomeTestData {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Test
    public void addSomeTestData() {
        //Given
        Item item1 = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        Item item2 = new Item("Kontakt", new BigDecimal("22.00"), 8, new BigDecimal("20.50"));

        BasketItem basketItem1 = new BasketItem(14);
        BasketItem basketItem2 = new BasketItem(10);

        item1.getBasketItems().add(basketItem1);
        item2.getBasketItems().add(basketItem2);

        basketItem1.setItem(item1);
        basketItem2.setItem(item2);

        Basket basket = new Basket(532, true);
        basket.getBasketItems().add(basketItem1);
        basket.getBasketItems().add(basketItem2);

        basketItem1.setBasket(basket);
        basketItem2.setBasket(basket);

        itemDao.save(item1);
        itemDao.save(item2);
        basketDao.save(basket);
    }
}