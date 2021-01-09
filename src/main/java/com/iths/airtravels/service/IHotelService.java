package com.iths.airtravels.service;

import com.iths.airtravels.entity.Hotel;

import java.util.List;

public interface IHotelService{

    Hotel addHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotel(Long id);
    void deleteHotel(Hotel hotel);
    Hotel saveHotel(Hotel hotel);
}
