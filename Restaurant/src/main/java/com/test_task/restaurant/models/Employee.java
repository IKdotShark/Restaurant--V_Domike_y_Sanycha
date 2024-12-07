package com.test_task.restaurant.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole staffRole;

    @ManyToMany(mappedBy = "employees")
    private Set<Orders> orders;

    public enum StaffRole {
        COOKER,
        WAITER,
        WASHER,
        MANAGER,
        BARMAN,
        SECURITY
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
