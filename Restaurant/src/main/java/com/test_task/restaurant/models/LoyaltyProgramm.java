package com.test_task.restaurant.models;

import jakarta.persistence.*;

@Entity
public class LoyaltyProgramm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// WARNING: Xуй его, надо или нет

    @Column(nullable = false, unique = true)
    private String bonusCard;// номер: 48948189591161

    @Column(nullable = false)
    private double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBonusCard() {
        return bonusCard;
    }

    public void setBonusCard(String bonusCard) {
        this.bonusCard = bonusCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
