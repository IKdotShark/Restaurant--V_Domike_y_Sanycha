package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.LoyaltyProgramm;
import com.test_task.restaurant.repositories.LoyaltyProgrammRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyProgrammService {

    private final LoyaltyProgrammRepository loyaltyProgrammRepository;
    private static final SecureRandom random = new SecureRandom();

    public LoyaltyProgrammService(LoyaltyProgrammRepository loyaltyProgrammRepository) {
        this.loyaltyProgrammRepository = loyaltyProgrammRepository;
    }

    public LoyaltyProgramm createLoyaltyProgram(LoyaltyProgramm loyaltyProgram) {
        return loyaltyProgrammRepository.save(loyaltyProgram);
    }

    public LoyaltyProgramm findLoyaltyProgramById(Long id) {
        return loyaltyProgrammRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found loyalty program with such id: " + id));
    }

    public LoyaltyProgramm findByCardNumber(String cardNumber) {
        return loyaltyProgrammRepository.findByBonusCard(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty program not found with card number: " + cardNumber));
    }

    public List<LoyaltyProgramm> findAllLoyaltyPrograms() {
        return loyaltyProgrammRepository.findAll();
    }

    public LoyaltyProgramm addBalance(Long id, double amount) {
        LoyaltyProgramm program = findLoyaltyProgramById(id);
        program.setBalance(program.getBalance() + amount);
        return loyaltyProgrammRepository.save(program);
    }

    public LoyaltyProgramm deductBalance(Long id, double amount) {
        LoyaltyProgramm program = findLoyaltyProgramById(id);
        if (program.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        program.setBalance(program.getBalance() - amount);
        return loyaltyProgrammRepository.save(program);
    }

    public String generateBonusCardNumber() {
        return generateCardNumber();
    }

    private static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
}