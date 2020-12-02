package com.iths.airtravels.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal totalPrice;

    @OneToOne
    private User user;

    @OneToOne
    private Flight flightID;

    @OneToOne
    private Hotel hotel;


    public Ticket(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Ticket(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlightID() {
        return flightID;
    }

    public void setFlightID(Flight flightID) {
        this.flightID = flightID;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
