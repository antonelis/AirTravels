package com.iths.airtravels.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "flight_fromLocation")
    private Location fromLocation;

    @ManyToOne
    @JoinColumn(name = "flight_toLocation")
    private Location toLocation;

    public Flight(BigDecimal price) {
        this.price = price;
    }

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
