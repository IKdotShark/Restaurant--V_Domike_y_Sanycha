package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
