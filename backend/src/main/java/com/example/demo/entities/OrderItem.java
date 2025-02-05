package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;
import com.example.demo.entities.Booking;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal tax;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    // The OneToMany relationship to Booking
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private Set<Booking> bookings; // One order item can have many bookings
}
