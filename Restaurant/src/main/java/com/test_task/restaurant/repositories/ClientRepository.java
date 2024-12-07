package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
