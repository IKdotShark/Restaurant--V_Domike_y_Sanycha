package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
