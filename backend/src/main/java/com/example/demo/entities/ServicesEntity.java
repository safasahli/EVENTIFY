package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
public class ServicesEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    @PositiveOrZero(message = "Price must be positive")
    @Column(name = "base_price", precision = 10, scale = 2)
    private BigDecimal basePrice;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany
    private List<Media> CategoryImages;

}
