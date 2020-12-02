package com.iths.airtravels.service;

import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public Hotel createHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> findHotelById(Long id){
        return hotelRepository.findById(id);
    }

    public Iterable<Hotel> findAllHotels(){
        return hotelRepository.findAll();
    }

    public void deleteHotelById(Long id){
        Optional<Hotel> foundHotel = hotelRepository.findById(id);
        hotelRepository.deleteById(foundHotel.get().getId());
    }


    public Iterable<Hotel> findHotelsByLocationId(Long id){
        return hotelRepository.findHotelsByLocationId(id);
    }
}
