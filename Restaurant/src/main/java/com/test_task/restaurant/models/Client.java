package com.test_task.restaurant.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String contact;

    @Column(unique = true)
    private String email;

    @Column()
    private String adress;

    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bonus_card_id", referencedColumnName = "id")
    private LoyaltyProgramm bonusCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LoyaltyProgramm getBonusCard() {
        return bonusCard;
    }

    public void setBonusCard(LoyaltyProgramm bonusCard) {
        this.bonusCard = bonusCard;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
