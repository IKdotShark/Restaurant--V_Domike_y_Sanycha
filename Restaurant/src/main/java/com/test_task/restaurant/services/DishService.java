package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Dish;
import com.test_task.restaurant.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish findDishById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found dish with such " + id));
    }

    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

    public void deleteDishById(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isEmpty()) throw new ResourceNotFoundException("Not found dish with such " + id);
        dishRepository.deleteById(id);
    }


}
