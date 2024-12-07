package com.test_task.restaurant.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private Status status;
    // TODO: Где жрать и адрес для доставки, снятие всех денег (оплата)
    @ManyToOne // в говне мб
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public enum Status {
        DELIVERED,
        COOKING,
        DELIVERING,
        ACCEPTED
    }

    @PrePersist
    private void onCreate() {
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
