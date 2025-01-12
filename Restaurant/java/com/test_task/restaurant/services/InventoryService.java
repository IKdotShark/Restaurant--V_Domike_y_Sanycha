package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Inventory;
import com.test_task.restaurant.models.Supplier;
import com.test_task.restaurant.repositories.InventoryRepository;
import com.test_task.restaurant.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;

    public InventoryService(InventoryRepository inventoryRepository, SupplierRepository supplierRepository) {
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public Inventory createInventory(Inventory inventory) {

        if (!inventoryRepository.findByProductName(inventory.getProductName()).isEmpty()) {
            throw new RuntimeException("Такая параша есть уже!");
        }

        Optional<Supplier> supplier = supplierRepository.findById(inventory.getSupplier().getId());
        supplier.ifPresent(inventory::setSupplier);
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        Inventory inventory = findInventoryById(id);

        if (inventoryDetails.getProductName() != null) {
            inventory.setProductName(inventoryDetails.getProductName());
        }
        if (inventoryDetails.getQuantity() > 0) {
            inventory.setQuantity(inventoryDetails.getQuantity());
        }
        Optional<Supplier> supplier = supplierRepository.findById(inventoryDetails.getSupplier().getId());
        Supplier foundSupplier = supplier
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + inventoryDetails.getSupplier().getId()));
        inventory.setSupplier(foundSupplier);

        return inventoryRepository.save(inventory);
    }

    public Inventory findInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found inventory with such id: " + id));
    }

    public List<Inventory> findByProductName(String productName) {
        return inventoryRepository.findByProductName(productName);
    }

    public List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }

    public void deleteInventoryById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isEmpty()) throw new ResourceNotFoundException("Not found inventory with such id: " + id);
        inventoryRepository.deleteById(id);
    }
}
