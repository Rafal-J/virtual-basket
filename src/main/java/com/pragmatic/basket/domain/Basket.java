package com.pragmatic.basket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "Basket.findOpenBasket",
        query = "SELECT * FROM Baskets WHERE User_ID = :USER_ID and open = TRUE"
)

@NoArgsConstructor
@Entity
@Table(name = "BASKETS")
@Getter
@Setter

public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Basket_ID", unique = true)
    private int id;

    @Column(name = "User_ID")
    private int user_id;

    @Column(name = "Open")
    private boolean open;

    @OneToMany(
            targetEntity = BasketItem.class,
            mappedBy = "basket",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BasketItem> basketItems = new ArrayList<>();

    public Basket(int user_id, boolean open) {
        this.user_id = user_id;
        this.open = open;
    }


}
