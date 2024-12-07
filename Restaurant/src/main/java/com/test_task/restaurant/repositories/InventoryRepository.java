package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
