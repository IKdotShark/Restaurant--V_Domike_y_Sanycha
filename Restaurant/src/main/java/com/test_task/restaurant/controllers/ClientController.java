package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientByid(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> findAllClients() {
        List<Client> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping()
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client clientInfo, @PathVariable Long id) {
        Client client = clientService.findClientById(id);
        client.setName(clientInfo.getName());
        client.setBirthday(clientInfo.getBirthday());
        client.setContact(clientInfo.getContact());
        client.setEmail(clientInfo.getEmail());
        client.setBonusCard(clientInfo.getBonusCard());
        clientService.saveClient(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
