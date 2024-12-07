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

    public Inventory findInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found inventory with such id: " + id));
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
