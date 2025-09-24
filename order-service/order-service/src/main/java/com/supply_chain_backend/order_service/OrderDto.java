package com.supply_chain_backend.order_service;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private String customerName;
    private String productSku;
    private int quantity;
}
