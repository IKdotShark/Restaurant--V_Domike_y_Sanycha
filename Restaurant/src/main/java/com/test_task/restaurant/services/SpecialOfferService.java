package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.SpecialOffer;
import com.test_task.restaurant.repositories.SpecialOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialOfferService {

    private final SpecialOfferRepository specialOfferRepository;

    public SpecialOfferService(SpecialOfferRepository specialOfferRepository) {
        this.specialOfferRepository = specialOfferRepository;
    }

    public SpecialOffer createSpecialOffer(SpecialOffer specialOffer) {
        return specialOfferRepository.save(specialOffer);
    }

    public SpecialOffer findSpecialOfferById(Long id) {
        return specialOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found special offer with such id: " + id));
    }

    public List<SpecialOffer> findAllSpecialOffers() {
        return specialOfferRepository.findAll();
    }

    public void deleteSpecialOfferById(Long id) {
        Optional<SpecialOffer> specialOffer = specialOfferRepository.findById(id);
        if (specialOffer.isEmpty()) throw new ResourceNotFoundException("Not found special offer with such id: " + id);
        specialOfferRepository.deleteById(id);
    }
}
