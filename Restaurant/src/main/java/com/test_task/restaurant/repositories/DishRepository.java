package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
