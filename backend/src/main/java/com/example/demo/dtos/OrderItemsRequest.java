package com.example.demo.dtos;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsRequest {

    private Long productId;
    private int quantity;
    private BigDecimal price;
}
