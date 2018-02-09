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
    @Column(name = "basket_item_id", unique = true)
    private int id;

    @Column(name = "qty")
    private int qty;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    @JsonIgnore
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public BasketItem(int qty) {
        this.qty = qty;
    }
}