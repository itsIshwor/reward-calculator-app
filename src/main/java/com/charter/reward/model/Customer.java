package com.charter.reward.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Purchase> purchases = new ArrayList<>();

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer addToPurchases(Purchase purchase) {
        this.purchases.add(purchase);
        purchase.setCustomer(this);
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}
