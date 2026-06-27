package com.leo.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    private String observation;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    public void sumTotalPrice() {
        BigDecimal unitPrice = this.unitPrice;
        Long quantity = this.quantity;

        if (unitPrice == null) {
            unitPrice = BigDecimal.ZERO;
        }

        if (quantity == null) {
            quantity = 0L;
        }

        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }

    public void registerEntry(Long quantity) {
        this.quantity += quantity;
    }

    public void registerExit(Long quantity) {
        this.quantity -= quantity;
    }

}
