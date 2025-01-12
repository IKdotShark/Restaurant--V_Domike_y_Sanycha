package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.services.ClientService;
import com.test_task.restaurant.services.LoyaltyProgrammService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final LoyaltyProgrammService loyaltyProgrammService;

    public ClientController(ClientService clientService, LoyaltyProgrammService loyaltyProgrammService) {
        this.clientService = clientService;
        this.loyaltyProgrammService = loyaltyProgrammService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> findAllClients() {
        List<Client> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/card/{card_id}")
    public ResponseEntity<Client> findClientByCardBonus(@PathVariable Long card_id) {
        Client client = clientService.findClientByBonusCardId(card_id);
        return ResponseEntity.ok(client);
    }

    @PostMapping()
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client clientInfo, @PathVariable Long id) {
        Client client = clientService.findClientById(id);
        clientService.updateClient(client, clientInfo);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{clientId}/delete/bonus-card")
    public ResponseEntity<Client> removeBonusCard(@PathVariable Long clientId) {
        Client updatedClient = clientService.removeBonusCard(clientId);
        return ResponseEntity.ok(updatedClient);
    }
}