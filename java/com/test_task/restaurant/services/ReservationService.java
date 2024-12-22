package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Reservation;
import com.test_task.restaurant.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found reservation with such id: " + id));
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByHall(Reservation.Hall hall) {
        return reservationRepository.findByHall(hall);
    }

    public List<Reservation> findByClient(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    public List<Reservation> findByReservationDate(LocalDateTime date) {
        return reservationRepository.findByReservationDate(date);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        validateReservation(reservation);
        Reservation existingReservation = findReservationById(id);
        existingReservation.setReservationDate(reservation.getReservationDate());
        existingReservation.setClient(reservation.getClient());
        existingReservation.setHall(reservation.getHall());
        return reservationRepository.save(existingReservation);
    }

    private void validateReservation(Reservation reservation) {
        if (reservation.getReservationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservation date must be in the future.");
        }
    }

    public void deleteReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) throw new ResourceNotFoundException("Not found reservation with such id: " + id);
        reservationRepository.deleteById(id);
    }
}
