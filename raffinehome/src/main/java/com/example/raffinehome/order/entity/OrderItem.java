package com.example.raffinehome.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.raffinehome.product.entity.Product;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)
    private  int unitPrice;
    
    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    private int subtotal;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
    return "OrderItem{id=" + id
            + ", product=" + (product != null ? product.getId() : null)
            // ...他の必要フィールド
            + ", orderId=" + (order != null ? order.getId() : null)
            + '}';
}
}