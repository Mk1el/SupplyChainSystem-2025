package com.supply_chain_backend.inventory_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(inventoryService.addProduct(dto));
    }

    @PutMapping("/{sku}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable String sku,
                                               @RequestParam int quantityChange) {
        return ResponseEntity.ok(inventoryService.updateStock(sku, quantityChange));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProduct(@PathVariable String sku) {
        return ResponseEntity.ok(inventoryService.getProduct(sku));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku) {
        inventoryService.removeProduct(sku);
        return ResponseEntity.noContent().build();
    }
}
