package com.test_task.restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @JsonIgnore
    private String ingredients;

    @Column(nullable = false)
    private String description;

    @Transient
    @JsonProperty("ingredients")
    private List<String> transientIngredients;

    @Column(name = "category")
    private String category;

    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    @JsonIgnore
    private Menu menu;

    @Column(name = "image")
    private String src;

    public Dish() {}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTransientIngredients() {
        return transientIngredients;
    }

    public void setTransientIngredients(List<String> transientIngredients) {
        this.transientIngredients = transientIngredients;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
