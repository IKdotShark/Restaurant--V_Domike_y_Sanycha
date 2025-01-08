package com.test_task.restaurant.Dto;

import com.test_task.restaurant.models.Client;

import java.util.List;

public class OrderRequest {

    private Client client;
    private String status;
    private List<Long> dishesIds;
    private List<Long> drinksIds;
    private List<Long> desertsIds;

    public OrderRequest() {}

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getDishesIds() {
        return dishesIds;
    }

    public void setDishesIds(List<Long> dishesIds) {
        this.dishesIds = dishesIds;
    }

    public List<Long> getDrinksIds() {
        return drinksIds;
    }

    public void setDrinksIds(List<Long> drinksIds) {
        this.drinksIds = drinksIds;
    }

    public List<Long> getDesertsIds() {
        return desertsIds;
    }

    public void setDesertsIds(List<Long> desertsIds) {
        this.desertsIds = desertsIds;
    }
}
