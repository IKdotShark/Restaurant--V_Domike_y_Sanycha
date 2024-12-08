package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.LoyaltyProgramm;
import com.test_task.restaurant.services.LoyaltyProgrammService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loyalty_program")
public class LoyaltyProgrammController {

    private final LoyaltyProgrammService loyaltyProgrammService;

    public LoyaltyProgrammController(LoyaltyProgrammService loyaltyProgrammService) {
        this.loyaltyProgrammService = loyaltyProgrammService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyProgramm> getLP(@PathVariable Long id) {
        LoyaltyProgramm loyaltyProgramm = loyaltyProgrammService.findLoyaltyProgramById(id);
        return ResponseEntity.ok(loyaltyProgramm);
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<LoyaltyProgramm> getProgramByCard(@PathVariable String cardNumber) {
        return ResponseEntity.ok(loyaltyProgrammService.findByCardNumber(cardNumber));
    }

    @PostMapping()
    public ResponseEntity<LoyaltyProgramm> createLP(@RequestBody LoyaltyProgramm loyaltyProgramm) {
        LoyaltyProgramm createdLoyaltyProgramm = loyaltyProgrammService.createLoyaltyProgram(loyaltyProgramm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoyaltyProgramm);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<LoyaltyProgramm> updateLP(
            @RequestBody LoyaltyProgramm loyaltyProgrammInfo,
            @PathVariable Long id) {
        LoyaltyProgramm loyaltyProgramm = loyaltyProgrammService.findLoyaltyProgramById(id);
        loyaltyProgramm.setBalance(loyaltyProgrammInfo.getBalance());
        loyaltyProgramm.setBonusCard(loyaltyProgrammInfo.getBonusCard());
        loyaltyProgrammService.createLoyaltyProgram(loyaltyProgramm);
        return ResponseEntity.ok(loyaltyProgramm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<LoyaltyProgramm> deleteLP(@PathVariable Long id) {
        loyaltyProgrammService.deleteLoyaltyProgramById(id);
        return ResponseEntity.noContent().build();
    }
}
