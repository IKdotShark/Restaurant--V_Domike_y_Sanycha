package com.test_task.restaurant.repositories;

import com.test_task.restaurant.models.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
}
