package com.test_task.restaurant.Dto;

import com.test_task.restaurant.models.Desert;

import java.util.List;

public class DesertRequest {
    private List<Desert> desertList;

    public List<Desert> getDesertList() {
        return desertList;
    }

    public void setDesertList(List<Desert> desertList) {
        this.desertList = desertList;
    }
}
