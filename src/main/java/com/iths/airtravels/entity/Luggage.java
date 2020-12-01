package com.iths.airtravels.entity;

import javax.persistence.*;

@Entity
public class Luggage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String size;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "luggage_user")
    private User user;

    public Luggage(String size, double weight) {
        this.size = size;
        this.weight = weight;
    }

    public Luggage() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String quantity) {
        this.size = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}