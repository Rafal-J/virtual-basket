package com.pragmatic.basket.domain;

import com.pragmatic.basket.domain.dao.BasketDao;
import com.pragmatic.basket.domain.dao.BasketItemDao;
import com.pragmatic.basket.domain.dao.ItemDao;
import com.pragmatic.basket.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoveBasketItemTest {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    BasketItemDao basketItemDao;

    @Autowired
    DbService dbService;

    @Test
    public void testRemoveItemFromBasket() {
        //Given
        Item item1 = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        Item item2 = new Item("Kontakt", new BigDecimal("22.00"), 8, new BigDecimal("20.50"));

        BasketItem basketItem1 = new BasketItem(14);
        BasketItem basketItem2 = new BasketItem(10);

        basketItem1.setItem(item1);
        basketItem2.setItem(item2);

        Basket basket = new Basket(12, true);
        basket.getBasketItems().add(basketItem1);
        basket.getBasketItems().add(basketItem2);

        basketItem1.setBasket(basket);
        basketItem2.setBasket(basket);

        //When
        itemDao.save(item1);
        itemDao.save(item2);
        basketDao.save(basket);
        basket = dbService.removeItemFromBasket(basket.getId(), item1.getId());
        List<BasketItem> basketItems = basketItemDao.findBasketItemsByBasketId(basket.getId());

        //Then
        Assert.assertEquals(1, basketItems.size());

        //CleanUp
        basketDao.delete(basket);
        itemDao.delete(item1.getId());
        itemDao.delete(item2.getId());
    }
}