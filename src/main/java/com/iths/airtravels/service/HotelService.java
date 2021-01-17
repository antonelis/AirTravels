package com.iths.airtravels.service;

import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Iterable<Hotel> findHotelsByLocationId(Long id){
        return hotelRepository.findHotelsByLocationId(id);
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        return  hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(Long id) {
        return hotelRepository.getOne(id);
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return  hotelRepository.save(hotel);
    }
}
