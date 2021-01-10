package com.iths.airtravels.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categories> categories;

    public Hotel(){

    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
