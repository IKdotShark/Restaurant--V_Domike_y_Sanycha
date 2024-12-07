package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.LoyaltyProgramm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyProgrammRepository extends JpaRepository<LoyaltyProgramm, Long> {
}
