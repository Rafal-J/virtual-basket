package com.pragmatic.basket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "BASKETID")
    @JsonIgnore
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "ITEMID")
    private Item item;

    public BasketItem(int qty) {
        this.qty = qty;
    }
}
