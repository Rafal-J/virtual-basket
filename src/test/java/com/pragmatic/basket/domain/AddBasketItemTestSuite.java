package com.pragmatic.basket.domain;

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
public class AddBasketItemTestSuite {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    BasketItemDao basketItemDao;

    @Autowired
    DbService dbService;

    @Test
    public void testAddingNewItemsToExistingBasket() {
        //Given
        Item item1 = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        Item item2 = new Item("Kontakt", new BigDecimal("22.00"), 8, new BigDecimal("20.50"));
        Basket basket = new Basket(12, true);

        //When
        itemDao.save(item1);
        itemDao.save(item2);
        basketDao.save(basket);
        Basket basket2 = dbService.addBasketItem(12, item1.getId(), 10);
        basket2 = dbService.addBasketItem(12, item2.getId(), 15);
        List<BasketItem> basketItems = basketItemDao.findBasketItemsByBasketId(basket.getId());

        //Then
        Assert.assertTrue(basketItems.size() > 1);

        //CleanUp
        basketDao.delete(basket2);
        itemDao.delete(item1.getId());
        itemDao.delete(item2.getId());
    }

    @Test
    public void testAddingNewItemsToNewBasket() {
        //Given
        Item item1 = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        Item item2 = new Item("Kontakt", new BigDecimal("22.00"), 8, new BigDecimal("20.50"));

        //When
        itemDao.save(item1);
        itemDao.save(item2);
        Basket basket = dbService.addBasketItem(24, item2.getId(), 20);
        System.out.println(basket.getBasketItems().size());
        List<BasketItem> basketItems = basketItemDao.findBasketItemsByBasketId(basket.getId());

        //Then
        Assert.assertTrue(basketItems.size() > 0);

        //CleanUp
        basketDao.delete(basket);
        itemDao.delete(item1.getId());
        itemDao.delete(item2.getId());

    }

    @Test
    public void testUpdateExistingBasketItem() {
        //Given
        Item item1 = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        Item item2 = new Item("Kontakt", new BigDecimal("22.00"), 8, new BigDecimal("20.50"));
        Basket basket = new Basket(12, true);

        //When
        itemDao.save(item1);
        itemDao.save(item2);
        basketDao.save(basket);
        dbService.addBasketItem(12, item1.getId(), 10);
        dbService.addBasketItem(12, item2.getId(), 15);
        Basket basket2 = dbService.addBasketItem(12, item2.getId(), 8);
        List<BasketItem> basketItems = basketItemDao.findBasketItemsByBasketId(basket.getId());

        //Then
        Assert.assertTrue(basketItems.size() == 2);

        //CleanUp
        basketDao.delete(basket2);
        itemDao.delete(item1.getId());
        itemDao.delete(item2.getId());
    }
}