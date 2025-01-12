package com.test_task.restaurant.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test_task.restaurant.models.Orders;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequest {

    private String employee;

    private Set<Orders> orders;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
