package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Inventory;
import com.test_task.restaurant.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(Inventory inventory) {
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
        inventory.setSupplier(inventoryDetails.getSupplier());

        return inventoryRepository.save(inventory);
    }

    public Inventory findInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found inventory with such id: " + id));
    }

    public List<Inventory> findByProductName(String productName) {
        return inventoryRepository.findByProductNameContainingIgnoreCase(productName);
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
