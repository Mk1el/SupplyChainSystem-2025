package com.supply_chain_backend.supplier_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    // Supplier Endpoints
    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Purchase Order Endpoints
    @PostMapping("/{supplierId}/orders")
    public PurchaseOrder createOrder(@PathVariable Long supplierId, @RequestBody PurchaseOrder order) {
        return supplierService.createPurchaseOrder(supplierId, order);
    }

    @GetMapping("/orders/pending")
    public List<PurchaseOrder> getPendingOrders() {
        return supplierService.getPendingOrders();
    }

    @PutMapping("/orders/{orderId}/complete")
    public PurchaseOrder markOrderCompleted(@PathVariable Long orderId) {
        return supplierService.markOrderCompleted(orderId);
    }

    @PutMapping("/orders/{orderId}/delay")
    public PurchaseOrder markOrderDelayed(@PathVariable Long orderId) {
        return supplierService.markOrderDelayed(orderId);
    }
}
