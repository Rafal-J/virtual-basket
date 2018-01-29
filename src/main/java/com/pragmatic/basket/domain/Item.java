package com.pragmatic.basket.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "ITEMS")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Item_ID", unique = true)
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "DiscountAtQty")
    private int discountAtQty;

    @Column(name = "DiscountedPrice")
    private BigDecimal discountedPrice;

    @OneToMany(
            targetEntity = BasketItem.class,
            mappedBy = "item"
    )
    private List<BasketItem> basketItems = new ArrayList<>();

    public Item(String name, BigDecimal price, int discountAtQty, BigDecimal discountedPrice) {
        this.name = name;
        this.price = price;
        this.discountAtQty = discountAtQty;
        this.discountedPrice = discountedPrice;
    }
}
