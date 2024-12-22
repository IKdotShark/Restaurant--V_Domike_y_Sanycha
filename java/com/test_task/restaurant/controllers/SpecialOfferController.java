package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.SpecialOffer;
import com.test_task.restaurant.services.SpecialOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/special-offers")
public class SpecialOfferController {

    private final SpecialOfferService specialOfferService;

    public SpecialOfferController(SpecialOfferService specialOfferService) {
        this.specialOfferService = specialOfferService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialOffer>> getAllOffers() {
        return ResponseEntity.ok(specialOfferService.findAllSpecialOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialOffer> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(specialOfferService.findSpecialOfferById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<SpecialOffer>> getActiveOffers() {
        return ResponseEntity.ok(specialOfferService.findActiveOffers());
    }

    @PostMapping
    public ResponseEntity<SpecialOffer> createOffer(@RequestBody SpecialOffer offer) {
        SpecialOffer createdOffer = specialOfferService.createSpecialOffer(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialOffer> updateOffer(@PathVariable Long id, @RequestBody SpecialOffer offer) {
        SpecialOffer updatedOffer = specialOfferService.updateSpecialOffer(id, offer);
        return ResponseEntity.ok(updatedOffer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        specialOfferService.deleteSpecialOfferById(id);
        return ResponseEntity.noContent().build();
    }


}
