package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.SpecialOffer;
import com.test_task.restaurant.repositories.SpecialOfferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<SpecialOffer> findActiveOffers() {
        LocalDateTime now = LocalDateTime.now();
        return specialOfferRepository.findByStartDateBeforeAndEndDateAfter(now, now);
    }

    public SpecialOffer updateSpecialOffer(Long id, SpecialOffer offer) {
        validateOffer(offer);
        SpecialOffer existingOffer = findSpecialOfferById(id);
        existingOffer.setNewPrice(offer.getNewPrice());
        existingOffer.setStartDate(offer.getStartDate());
        existingOffer.setEndDate(offer.getEndDate());
        existingOffer.setDish(offer.getDish());
        existingOffer.setDrink(offer.getDrink());
        existingOffer.setDesert(offer.getDesert());
        return specialOfferRepository.save(existingOffer);
    }

    private void validateOffer(SpecialOffer offer) {
        int count = 0;
        if (offer.getDish() != null) count++;
        if (offer.getDrink() != null) count++;
        if (offer.getDesert() != null) count++;

        if (count != 1) {
            throw new IllegalArgumentException("Only one of Dish, Drink, or Desert must be set for a special offer.");
        }
    }

    public void deleteSpecialOfferById(Long id) {
        Optional<SpecialOffer> specialOffer = specialOfferRepository.findById(id);
        if (specialOffer.isEmpty()) throw new ResourceNotFoundException("Not found special offer with such id: " + id);
        specialOfferRepository.deleteById(id);
    }
}
