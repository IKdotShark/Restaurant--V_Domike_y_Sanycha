package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
