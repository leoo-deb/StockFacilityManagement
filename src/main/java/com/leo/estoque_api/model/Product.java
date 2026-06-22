package com.leo.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    public void sumTotalPrice() {
        BigDecimal price = this.price;
        Long quantity = this.quantity;

        if (price == null) {
            price = BigDecimal.ZERO;
        }

        if (quantity == null) {
            quantity = 0L;
        }

        this.totalValue = price.multiply(new BigDecimal(quantity));
    }

    public Boolean isActive() {
        return active;
    }

    public void registerEntry(Long quantity) {
        this.quantity += quantity;
    }

    public void registerExit(Long quantity) {
        this.quantity -= quantity;
    }

}
