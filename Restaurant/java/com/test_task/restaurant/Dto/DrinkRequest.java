package com.test_task.restaurant.Dto;

import com.test_task.restaurant.models.Drink;

import java.util.List;

public class DrinkRequest {

    private List<Drink> drinkList;

    public List<Drink> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }
}
