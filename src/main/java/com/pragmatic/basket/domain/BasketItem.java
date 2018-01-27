package com.pragmatic.basket.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Table(name = "BASKETITEMS")
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
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public BasketItem(int qty) {
        this.qty = qty;
    }
}
