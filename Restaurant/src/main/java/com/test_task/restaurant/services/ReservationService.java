package com.test_task.restaurant.services;

import com.test_task.restaurant.Dto.ReservationRequest;
import com.test_task.restaurant.Dto.ReservationUpdateRequest;
import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.models.Reservation;
import com.test_task.restaurant.repositories.ClientRepository;
import com.test_task.restaurant.repositories.ReservationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    public Reservation createReservation(ReservationRequest reservationRequest) {
        Client client = clientRepository.findByContact(reservationRequest.getPhoneNUmber())
                .orElseGet(() -> createNewClient(reservationRequest));

        updateClientIfNeeded(client, reservationRequest);

        Reservation reservation = new Reservation();
        reservation.setReservationDate(parseDateTime(reservationRequest.getDateTime()));
        reservation.setClient(client);
        reservation.setHall(resolveTableType(reservationRequest.getTableType()));

        validateReservationDate(reservation.getReservationDate());

        return reservationRepository.save(reservation);
    }

    private Client createNewClient(ReservationRequest reservationRequest) {
        Client newClient = new Client();
        newClient.setName(reservationRequest.getName());
        newClient.setContact(reservationRequest.getPhoneNUmber());
        newClient.setEmail(reservationRequest.getEmail());
        return clientRepository.save(newClient);
    }

    private void updateClientIfNeeded(Client client, ReservationRequest reservationRequest) {
        boolean updated = false;
        if (!client.getName().equals(reservationRequest.getName())) {
            client.setName(reservationRequest.getName());
            updated = true;
        }
        if (!client.getEmail().equals(reservationRequest.getEmail())) {
            client.setEmail(reservationRequest.getEmail());
            updated = true;
        }
        if (updated) {
            clientRepository.save(client);
        }
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'dd.MM.yyyy HH:mm'.", e);
        }
    }

    private Reservation.Hall resolveTableType(String tableType) {
        return switch (tableType.toLowerCase()) {
            case "vip" -> Reservation.Hall.VIP;
            case "common" -> Reservation.Hall.COMMON;
            case "table_with_sanych" -> Reservation.Hall.TABLE_WITH_SANYCH;
            default -> throw new ResourceNotFoundException("Not found such table");
        };
    }

    private void validateReservationDate(LocalDateTime reservationDate) {
        if (reservationDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservation date must be in the future.");
        }
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

    public Reservation updateReservation(Long id, ReservationUpdateRequest reservationUpdateRequest) {
        Reservation existingReservation = findReservationById(id);

        if (reservationUpdateRequest.getDateTime() != null && !reservationUpdateRequest.getDateTime().isEmpty()) {
            LocalDateTime newDateTime = parseDateTime(reservationUpdateRequest.getDateTime());
            validateReservationDate(newDateTime);
            existingReservation.setReservationDate(newDateTime);
        }

        if (reservationUpdateRequest.getTableType() != null && !reservationUpdateRequest.getTableType().isEmpty()) {
            existingReservation.setHall(resolveTableType(reservationUpdateRequest.getTableType()));
        }

        return reservationRepository.save(existingReservation);
    }

    public void deleteReservationById(Long id) {
        try {
            reservationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Not found reservation with such id: " + id);
        }
    }
}
