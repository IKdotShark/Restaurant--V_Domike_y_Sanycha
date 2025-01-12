package com.test_task.restaurant.Dto;

import com.test_task.restaurant.models.Dish;

import java.util.List;

public class DishRequest {
    private List<Dish> dishList;

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
