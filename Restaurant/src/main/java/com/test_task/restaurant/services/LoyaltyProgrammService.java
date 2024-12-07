package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.LoyaltyProgramm;
import com.test_task.restaurant.repositories.LoyaltyProgrammRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyProgrammService {

    private final LoyaltyProgrammRepository loyaltyProgrammRepository;

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

    public List<LoyaltyProgramm> findAllLoyaltyPrograms() {
        return loyaltyProgrammRepository.findAll();
    }

    public void deleteLoyaltyProgramById(Long id) {
        Optional<LoyaltyProgramm> loyaltyProgram = loyaltyProgrammRepository.findById(id);
        if (loyaltyProgram.isEmpty()) throw new ResourceNotFoundException("Not found loyalty program with such id: " + id);
        loyaltyProgrammRepository.deleteById(id);
    }

}
