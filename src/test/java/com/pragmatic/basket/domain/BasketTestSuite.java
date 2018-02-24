package com.pragmatic.basket.domain;

import com.pragmatic.basket.domain.dao.BasketDao;
import com.pragmatic.basket.domain.dao.ItemDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketTestSuite {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Test
    public void testAddingNewItemsAndBasket() {
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
        int id = basket.getId();

        //Then
        Assert.assertNotEquals(0, id);
        Assert.assertTrue(itemDao.count() >= 2L);

        //CleanUp
        basketDao.delete(basket);
        itemDao.delete(item1.getId());
        itemDao.delete(item2.getId());
    }

    @Test
    public void testGetOpenBasket() {
        //Given
        Basket basket1 = new Basket(112, true);
        Basket basket2 = new Basket(112, false);
        Basket basket3 = new Basket(112, false);

        //When
        basketDao.save(basket1);
        basketDao.save(basket2);
        basketDao.save(basket3);
        Basket basket4 = basketDao.findBasketByUserIdAndOpen(112, true);
        int id = basket4.getId();

        //Then
        Assert.assertNotEquals(0, id);

        //CleanUp
        basketDao.delete(basket1);
        basketDao.delete(basket2);
        basketDao.delete(basket3);
    }
}