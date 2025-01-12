package com.test_task.restaurant.controllers;

import com.test_task.restaurant.Dto.ReservationRequest;
import com.test_task.restaurant.Dto.ReservationUpdateRequest;
import com.test_task.restaurant.models.Reservation;
import com.test_task.restaurant.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findReservationById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation createdReservation = reservationService.createReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id,
                                                         @RequestBody ReservationUpdateRequest reservationUpdateRequest) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservationUpdateRequest);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hall/{hall}")
    public ResponseEntity<List<Reservation>> getReservationsByHall(@PathVariable Reservation.Hall hall) {
        return ResponseEntity.ok(reservationService.findByHall(hall));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Reservation>> getReservationsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(reservationService.findByClient(clientId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@PathVariable String date) {
        LocalDateTime reservationDate = LocalDateTime.parse(date);
        return ResponseEntity.ok(reservationService.findByReservationDate(reservationDate));
    }
}
