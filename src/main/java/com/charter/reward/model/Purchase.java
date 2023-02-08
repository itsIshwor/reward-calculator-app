package com.charter.reward.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private Instant timestamp;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Purchase(long id, BigDecimal amount, Instant now) {
        this.id = id;
        this.amount = amount;
        this.timestamp = now;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp+
                '}';
    }
}
