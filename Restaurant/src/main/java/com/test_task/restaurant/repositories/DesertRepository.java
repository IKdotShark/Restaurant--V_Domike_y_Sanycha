package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Desert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesertRepository extends JpaRepository<Desert, Long> {
    List<Desert> findAllById(Long id);
}
