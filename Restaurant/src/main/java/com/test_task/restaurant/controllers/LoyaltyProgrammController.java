package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.models.LoyaltyProgramm;
import com.test_task.restaurant.services.ClientService;
import com.test_task.restaurant.services.LoyaltyProgrammService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyalty_program")
public class LoyaltyProgrammController {

    private final LoyaltyProgrammService loyaltyProgrammService;

    private final ClientService clientService;

    public LoyaltyProgrammController(LoyaltyProgrammService loyaltyProgrammService, ClientService clientService) {
        this.loyaltyProgrammService = loyaltyProgrammService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<LoyaltyProgramm>> getAllLP() {
        List<LoyaltyProgramm> loyaltyProgrammsAll = loyaltyProgrammService.findAllLoyaltyPrograms();
        return ResponseEntity.ok(loyaltyProgrammsAll);
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<LoyaltyProgramm> getLP(@PathVariable Long client_id) {
        Client client = clientService.findClientById(client_id);
        LoyaltyProgramm loyaltyProgramm = client.getBonusCard();
        return ResponseEntity.ok(loyaltyProgramm);
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<LoyaltyProgramm> getProgramByCard(@PathVariable String cardNumber) {
        return ResponseEntity.ok(loyaltyProgrammService.findByCardNumber(cardNumber));
    }

    @PostMapping("/{client_id}")
    public ResponseEntity<Client> createLP(@RequestBody LoyaltyProgramm loyaltyProgramm,
                                           @PathVariable Long client_id) {

        if (loyaltyProgramm.getBonusCard() == null) {
            String bonusCard = loyaltyProgrammService.generateBonusCardNumber();
            loyaltyProgramm.setBonusCard(bonusCard);
        }

        LoyaltyProgramm createdLoyaltyProgramm = loyaltyProgrammService.createLoyaltyProgram(loyaltyProgramm);
        Client client = clientService.findClientById(client_id);
        client.setBonusCard(createdLoyaltyProgramm);
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PostMapping("/add-balance/{id}")
    public ResponseEntity<LoyaltyProgramm> addBalance(@PathVariable Long id, @RequestParam double amount) {
        LoyaltyProgramm updatedProgram = loyaltyProgrammService.addBalance(id, amount);
        return ResponseEntity.ok(updatedProgram);
    }

    @PostMapping("/deduct-balance/{id}")
    public ResponseEntity<LoyaltyProgramm> deductBalance(@PathVariable Long id, @RequestParam double amount) {
        LoyaltyProgramm updatedProgram = loyaltyProgrammService.deductBalance(id, amount);
        return ResponseEntity.ok(updatedProgram);
    }
}