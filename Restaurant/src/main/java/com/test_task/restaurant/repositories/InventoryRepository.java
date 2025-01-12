package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductName(String productName);
}
