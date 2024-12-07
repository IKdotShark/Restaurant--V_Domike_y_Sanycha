package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
