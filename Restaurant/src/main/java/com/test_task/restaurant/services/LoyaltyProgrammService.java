package com.test_task.restaurant.services;

import com.test_task.restaurant.Dto.CreateCardByPhoneRequest;
import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Client;
import com.test_task.restaurant.models.LoyaltyProgramm;
import com.test_task.restaurant.repositories.ClientRepository;
import com.test_task.restaurant.repositories.LoyaltyProgrammRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyProgrammService {

    private final LoyaltyProgrammRepository loyaltyProgrammRepository;
    private final ClientRepository clientRepository;
    private static final SecureRandom random = new SecureRandom();

    public LoyaltyProgrammService(LoyaltyProgrammRepository loyaltyProgrammRepository, ClientRepository clientRepository) {
        this.loyaltyProgrammRepository = loyaltyProgrammRepository;
        this.clientRepository = clientRepository;
    }

    public LoyaltyProgramm createLoyaltyProgram(LoyaltyProgramm loyaltyProgram) {
        return loyaltyProgrammRepository.save(loyaltyProgram);
    }

    public LoyaltyProgramm createLoyaltyProgram(CreateCardByPhoneRequest createCardByPhoneRequest) {
        Optional<Client> foundClient = clientRepository.findByContact("+" + createCardByPhoneRequest.getPhoneNumber());
        if (foundClient.isEmpty()) {
            throw new RuntimeException("Тебя здесь нет, долбоеб!");
        }
        Client client = foundClient.get();
        LoyaltyProgramm loyaltyProgramm = new LoyaltyProgramm();
        client.setBonusCard(loyaltyProgramm);
        loyaltyProgramm.setBonusCard(generateBonusCardNumber());
        loyaltyProgramm.setBalance(0.0);
        return loyaltyProgrammRepository.save(loyaltyProgramm);
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