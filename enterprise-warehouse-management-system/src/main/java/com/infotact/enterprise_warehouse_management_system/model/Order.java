package com.infotact.enterprise_warehouse_management_system.model;

import com.infotact.enterprise_warehouse_management_system.enums.OrderStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}