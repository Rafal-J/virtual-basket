package com.pragmatic.basket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BASKET_ITEMS")
@NoArgsConstructor
@Getter
@Setter
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "BasketItem_ID", unique = true)
    private int id;

    @Column(name = "QTY")
    private int qty;

    @ManyToOne
    @JoinColumn(name = "BASKET_ID")
    @JsonIgnore
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public BasketItem(int qty) {
        this.qty = qty;
    }
}