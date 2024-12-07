package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
