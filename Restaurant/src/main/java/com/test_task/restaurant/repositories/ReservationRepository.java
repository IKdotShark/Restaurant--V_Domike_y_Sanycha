package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByHall(Reservation.Hall hall);
    List<Reservation> findByClientId(Long clintId);
    List<Reservation> findByReservationDate(LocalDateTime reservationDate);

}
