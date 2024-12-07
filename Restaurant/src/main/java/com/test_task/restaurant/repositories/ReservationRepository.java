package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
