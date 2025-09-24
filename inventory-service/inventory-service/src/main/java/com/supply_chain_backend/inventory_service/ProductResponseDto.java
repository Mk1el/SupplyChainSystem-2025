package com.supply_chain_backend.inventory_service;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private String sku;
    private String name;
    private int quantity;
    private int reorderLevel;
    private String warehouseLocation;
}
