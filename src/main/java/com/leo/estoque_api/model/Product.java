package com.leo.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "products")
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

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        this.active = Boolean.TRUE;
    }

    public Boolean isActive() {
        return active;
    }

    public void toActive() {
        setActive(Boolean.TRUE);
    }

    public void toInactive() {
        setActive(Boolean.FALSE);
    }

}
