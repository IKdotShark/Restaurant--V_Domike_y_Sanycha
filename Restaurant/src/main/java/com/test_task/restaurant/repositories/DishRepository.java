package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllById(Long id);
}
