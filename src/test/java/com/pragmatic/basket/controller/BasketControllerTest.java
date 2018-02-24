package com.pragmatic.basket.controller;

import com.pragmatic.basket.domain.Basket;
import com.pragmatic.basket.domain.BasketItem;
import com.pragmatic.basket.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BasketController.class)
@RunWith(SpringRunner.class)
public class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketController basketController;

    private Basket prepareBasket() {
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

        return basket;
    }

    @Test
    public void shouldFetchUserBasket() throws Exception {
        //Given
        Basket basket = prepareBasket();
        when(basketController.getUserBasket(ArgumentMatchers.anyInt())).thenReturn(basket);

        //When@Then
        mockMvc.perform(get("/basket/17").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", is(12)))
                .andExpect(jsonPath("$.open", is(true)))
                .andExpect(jsonPath("$.basketItems", hasSize(2)));
    }


    @Test
    public void shouldFetchItemName() throws Exception {
        //Given
        when(basketController.getItemName(ArgumentMatchers.anyInt())).thenReturn("pietruszka");

        //When@Then
        mockMvc.perform(get("/basket/itemname/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", is("pietruszka")));

    }

    @Test
    public void shouldFetchItemPrice() throws Exception {
        //Given
        when(basketController.getItemPrice(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(BigDecimal.TEN);

        //When@Then
        mockMvc.perform(get("/basket/itemprice/15/12").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", is(10)));

    }

    @Test
    public void shouldFetchTotalAmount() throws Exception {
        //Given
        when(basketController.getBasketTotalAmount(ArgumentMatchers.anyInt())).thenReturn(BigDecimal.ONE);

        //When@Then
        mockMvc.perform(get("/basket/totalamount/200").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", is(1)));

    }

    @Test
    public void shouldAddBasketItem() throws Exception {
        //Given
        Basket basket = prepareBasket();
        when(basketController.addBasketItem(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(basket);

        //When@Then
        mockMvc.perform(post("/basket/basketitem/1/2/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", is(12)))
                .andExpect(jsonPath("$.open", is(true)))
                .andExpect(jsonPath("$.basketItems", hasSize(2)))
                .andExpect((jsonPath("$.basketItems[0].qty", is(14))));
    }

    @Test
    public void shouldUpdateBasketItem() throws Exception {
        //Given
        Basket basket = prepareBasket();
        when(basketController.updateBasketItem(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(basket);

        //When@Then
        mockMvc.perform(put("/basket/basketitem/1/2/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", is(12)))
                .andExpect(jsonPath("$.open", is(true)))
                .andExpect(jsonPath("$.basketItems", hasSize(2)))
                .andExpect((jsonPath("$.basketItems[1].id", is(0))));
    }

    @Test
    public void shouldCloseBasket() throws Exception {
        //Given
        Basket basket = prepareBasket();
        when(basketController.closeBasket(ArgumentMatchers.anyInt())).thenReturn(basket);

        //When@Then
        mockMvc.perform(put("/basket/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", is(12)))
                .andExpect(jsonPath("$.basketItems", hasSize(2)))
                .andExpect((jsonPath("$.basketItems[1].id", is(0))));
    }

    @Test
    public void shouldDeleteBasketItem() throws Exception {
        //Given
        Basket basket = prepareBasket();
        when(basketController.deleteBasketItem(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(basket);

        //When@Then
        mockMvc.perform(delete("/basket/basketitem/3/7").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", is(12)))
                .andExpect(jsonPath("$.basketItems", hasSize(2)))
                .andExpect((jsonPath("$.basketItems[0].id", is(0))));
    }
}