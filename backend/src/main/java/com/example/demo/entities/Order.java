package com.example.demo.entities;


import com.example.demo.security.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "tax_total", precision = 10, scale = 2)
    private BigDecimal taxTotal;

    @Column(name = "shipping_total", precision = 10, scale = 2)
    private BigDecimal shippingTotal;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Assurez-vous d'avoir une relation avec l'utilisateur
    private User user;
}