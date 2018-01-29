package com.pragmatic.basket.controller;

import com.pragmatic.basket.domain.Basket;
import com.pragmatic.basket.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Basket getUserBasket(@PathVariable int userId) {
        return dbService.getUserBasket(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/itemname/{itemId}")
    public String getItemName(@PathVariable int itemId) {
        return dbService.getItemName(itemId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/itemprice/{itemId}/{itemQty}")
    public BigDecimal getItemName(@PathVariable Integer itemId, @PathVariable Integer itemQty) {
        return dbService.getItemPrice(itemId, itemQty);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/totalamount/{basketId}")
    public BigDecimal getBasketTotalAmount(@PathVariable int basketId) {
        return dbService.getBasketTotalAmount(basketId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/basketitem/{userId}/{itemId}/{itemQty}")
    public Basket addBasketItem(@PathVariable Integer userId, @PathVariable Integer itemId, @PathVariable Integer itemQty) {
        System.out.println("W KOSZYKU: " + dbService.addBasketItem(userId, itemId, itemQty).getBasketItems().size());
        return dbService.addBasketItem(userId, itemId, itemQty);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/basketitem/{basketId}/{itemId}/{itemQty}")
    public Basket updateBasketItem(@PathVariable Integer basketId, @PathVariable Integer itemId, @PathVariable Integer itemQty) {
        return dbService.updateBasketItem(basketId, itemId, itemQty);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{basketId}")
    public Basket closeBasket(@PathVariable Integer basketId) {
        return dbService.closeBasket(basketId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/basketitem/{basketId}/{itemId}")
    public Basket addBasketItem(@PathVariable Integer basketId, @PathVariable Integer itemId) {
        return dbService.removeItemFromBasket(basketId, itemId);
    }
}