package com.ecommerce.Ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "item")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;
    @ManyToOne
    @JoinColumn
    OrderEntity orderEntity;
    @ManyToOne
    @JoinColumn
    Product product;

}
