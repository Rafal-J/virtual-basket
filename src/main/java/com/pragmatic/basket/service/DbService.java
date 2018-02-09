package com.pragmatic.basket.service;

import com.pragmatic.basket.domain.*;
import com.pragmatic.basket.domain.dao.BasketDao;
import com.pragmatic.basket.domain.dao.BasketItemDao;
import com.pragmatic.basket.domain.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
@Repository
public class DbService {
    @Autowired
    private BasketDao basketDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private BasketItemDao basketItemDao;

    public Basket getUserBasket(int userId) {
        return basketDao.findBasketByUserIdAndOpen(userId, true);
    }

    public Basket addBasketItem(int userId, int itemId, int itemQty) {
        try {
            Basket basket = basketDao.findBasketByUserIdAndOpen(userId, true);
            if (basket != null) {
                BasketItem tempBasketItem = basketItemDao.findBasketItemByBasketIdAndItemId(basket.getId(), itemId);
                if(tempBasketItem == null) {
                    basket = addNewBasketItemToExistingBasket(basket, itemId, itemQty);
                    return basket;
                }
                else {
                    basket = updateBasketItem(basket.getId(), itemId, itemQty);
                    return basket;
                }
            }
            else {
                basket = createNewBasketAndAddBasketItem(userId, itemId, itemQty);
                return basket;
            }
        } catch(IncorrectResultSizeDataAccessException o) {
            System.out.println("Użytkownik ma więcej niż jeden otwarty koszyk!");
        }
        return new Basket();
    }

    private Basket addNewBasketItemToExistingBasket(Basket basket, int itemId, int itemQty) {
        BasketItem basketItem = new BasketItem(itemQty);
        basketItem.setBasket(basket);
        basketItem.setItem(itemDao.findOne(itemId));
        basket.getBasketItems().add(basketItem);
        return basket;
    }
    private Basket createNewBasketAndAddBasketItem(int userId, int itemId, int itemQty) {
        Basket basket = new Basket(userId, true);
        BasketItem basketItem = new BasketItem(itemQty);
        basketItem.setItem(itemDao.findOne(itemId));
        basket.getBasketItems().add(basketItem);
        basketItem.setBasket(basket);
        basketDao.save(basket);
        return basket;
    }

    public Basket updateBasketItem (int basketId, int itemId, int itemQty) {
        Basket basket = basketDao.findOne(basketId);
        for (BasketItem basketItem1 : basket.getBasketItems()) {
            if (basketItem1.getItem().getId() == itemId) {
                basketItem1.setQty(itemQty);
            }
        }
        return basket;
    }

    public Basket removeItemFromBasket(int basketId, int itemId) {
        basketItemDao.deleteBasketItemByBasketIdAndItemId(basketId, itemId);
        Basket basket = basketDao.findOne(basketId);
        BasketItem basketItem = new BasketItem();
        for(BasketItem aBasketItem : basket.getBasketItems()) {
            if(aBasketItem.getItem().getId() == itemId) {
                basketItem = aBasketItem;
            }
        }
        basket.getBasketItems().remove(basketItem);
        return basket;
    }

    public String getItemName(int itemId) {
        Item item = itemDao.findOne(itemId);
        if(item != null) {
            return item.getName();
        }
        else {
            return "Brak towaru o podanym identyfikatorze";
        }
    }

    public BigDecimal getItemPrice(int itemId, int itemQty) {
        Item item = itemDao.findOne(itemId);
        BigDecimal price;
        if(item.getDiscountAtQty() > itemQty) {
            price = item.getPrice();
        }
        else {
            price = item.getDiscountedPrice();
        }
        return price;
    }

    public BigDecimal getBasketTotalAmount(int basketId) {
        Basket basket = basketDao.findOne(basketId);
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal tempPrice = BigDecimal.ZERO;
        for(BasketItem basketItem : basket.getBasketItems()) {
            tempPrice = getItemPrice(basketItem.getItem().getId(), basketItem.getQty());
            sum = sum.add(tempPrice.multiply(new BigDecimal(basketItem.getQty())));
        }
        return sum;
    }

    public Basket closeBasket(int basketId) {
        Basket basket = basketDao.findOne(basketId);
        basket.setOpen(false);
        return basket;
    }
}