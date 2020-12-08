package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/findall")
    public Iterable<Hotel> findAllHotels() {
        return hotelService.findAllHotels();
    }

    @GetMapping("/id/{id}")
    public Optional<Hotel> findHotelById(@PathVariable Long id) {
        return hotelService.findHotelById(id);
    }

    @GetMapping("/findbylocation/{id}")
    public Iterable<Hotel> findHotelsByLocationId(@PathVariable Long id){
        return  hotelService.findHotelsByLocationId(id);
    }

    @PostMapping("/create")
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
    }

}
