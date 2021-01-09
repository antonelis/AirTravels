package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.service.HotelService;
import com.iths.airtravels.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    Logger logger = LoggerFactory.getLogger(HotelController.class);

    private final HotelService hotelService;
    private final LocationService locationService;

    public HotelController(HotelService hotelService, LocationService locationService) {
        this.hotelService = hotelService;
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "hotel";
    }


    @GetMapping("/findall")
    public List<Hotel> findAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/id/{id}")
    public Hotel findHotelById(@PathVariable Long id) {
        return hotelService.getHotel(id);
    }

    @GetMapping("/findbylocation/{id}")
    public Iterable<Hotel> findHotelsByLocationId(@PathVariable Long id) {
        return hotelService.findHotelsByLocationId(id);
    }

    @PostMapping("/create")
    public String createHotel(@RequestParam(name = "location_id", defaultValue = "0") Long id,
                              @RequestParam(name = "hotel_name", defaultValue = "No Name") String name,
                              @RequestParam(name = "hotel_price") BigDecimal price) {

        Location lct = locationService.getLocation(id);

        if (lct != null) {
            Hotel hotel = new Hotel();
            hotel.setName(name);
            hotel.setPrice(price);
            hotel.setLocation(lct);

            hotelService.addHotel(hotel);
        }
        return "redirect:/hotel/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHotel(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        if (hotel != null) {
            hotelService.deleteHotel(hotel);
        }
        return "redirect:/hotel/";
    }

}
