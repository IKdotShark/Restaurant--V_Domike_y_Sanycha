package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Desert;
import com.test_task.restaurant.repositories.DesertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesertService {

    private final DesertRepository desertRepository;

    public DesertService(DesertRepository desertRepository) {
        this.desertRepository = desertRepository;
    }

    public Desert createDesert(Desert desert) {
        return desertRepository.save(desert);
    }

    public Desert findDesertById(Long id) {
        return desertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found desert with such " + id));
    }

    public List<Desert> findAllDeserts() {
        return desertRepository.findAll();
    }

    public void deleteDesertById(Long id) {
        Optional<Desert> desert = desertRepository.findById(id);
        if (desert.isEmpty()) throw new ResourceNotFoundException("Not found desert with such " + id);
        desertRepository.deleteById(id);
    }
}
