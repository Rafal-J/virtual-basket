package com.pragmatic.basket.domain;

import com.pragmatic.basket.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTestSuite {
    @Autowired
    BasketDao basketDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    DbService dbService;

    @Test
    public void testGetItemPrice() {
        //Given
        Item item = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        itemDao.save(item);

        //When
        BigDecimal normalPrice = dbService.getItemPrice(item.getId(), 4);
        BigDecimal discountedPrice = dbService.getItemPrice(item.getId(), 10);

        //Then
        Assert.assertEquals(new BigDecimal("10.51"), normalPrice);
        Assert.assertEquals(new BigDecimal("8.99"), discountedPrice);

        //CleanUp
        itemDao.delete(item);
    }

    @Test
    public void testGetItemName() {
        //Given
        Item item = new Item("Klamka", new BigDecimal("10.51"), 5, new BigDecimal("8.99"));
        itemDao.save(item);

        //When
        String itemName = dbService.getItemName(item.getId());
        String notFound = dbService.getItemName(0);

        //Then
        Assert.assertEquals("Klamka", itemName);
        Assert.assertEquals("Brak towaru o podanym identyfikatorze", notFound);

        //CleanUp
        itemDao.delete(item);
    }

}
