package com.pragmatic.basket.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BASKETS")
@NoArgsConstructor
@Getter
@Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Basket_ID", unique = true)
    private int id;

    @Column(name = "User_ID")
    private int userId;

    @Column(name = "Open")
    private boolean open;

    @OneToMany(
            targetEntity = BasketItem.class,
            mappedBy = "basket",
            cascade = CascadeType.ALL
    )
    private List<BasketItem> basketItems = new ArrayList<>();

    public Basket(int user_id, boolean open) {
        this.userId = user_id;
        this.open = open;
    }
}