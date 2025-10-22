package com.supply_chain_backend.supplier_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    // Manage Suppliers
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Manage Purchase Orders
    public PurchaseOrder createPurchaseOrder(Long supplierId, PurchaseOrder order) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        order.setSupplier(supplier);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return purchaseOrderRepository.save(order);
    }

    public List<PurchaseOrder> getPendingOrders() {
        return purchaseOrderRepository.findByStatus(OrderStatus.PENDING.name());
    }

    public PurchaseOrder markOrderCompleted(Long orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.COMPLETED);
        return purchaseOrderRepository.save(order);
    }

    public PurchaseOrder markOrderDelayed(Long orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.DELAYED);
        return purchaseOrderRepository.save(order);
    }
}
