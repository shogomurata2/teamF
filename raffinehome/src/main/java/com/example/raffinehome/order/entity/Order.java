package com.example.raffinehome.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private LocalDateTime orderDate;
    
    @Column(nullable = false)
    private Integer totalAmount;
    
    @Column(nullable = false)
    private String customerName;
    
    @Column(nullable = false)
    private String customerEmail;
    
    @Column(nullable = false)
    private String shippingAddress;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private String orderStatus;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderDetails = new ArrayList<>();
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Helper method to add order detail
    public void addOrderDetail(OrderItem orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    @Override
    public String toString() {
    return "Order{id=" + id
            + ", customerName='" + customerName + '\''
            // ...他の必要フィールド
            + ", orderDetails.size=" + (orderDetails != null ? orderDetails.size() : 0)
            + '}';
}
}

