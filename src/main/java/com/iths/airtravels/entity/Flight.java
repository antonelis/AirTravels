package com.iths.airtravels.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location fromLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location toLocation;

    public Flight(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

}
