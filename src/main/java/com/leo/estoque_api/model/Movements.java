package com.leo.estoque_api.model;

import com.leo.estoque_api.model.enums.TypeMovements;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Movements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @CreationTimestamp
    private OffsetDateTime date;

    @Column(nullable = false, length = 100)
    private String description;

    private TypeMovements type;

}
