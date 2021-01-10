package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Categories;
import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.service.CategoriesService;
import com.iths.airtravels.service.HotelService;
import com.iths.airtravels.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    Logger logger = LoggerFactory.getLogger(HotelController.class);

    private final HotelService hotelService;
    private final LocationService locationService;
    private final CategoriesService categoriesService;

    public HotelController(HotelService hotelService, LocationService locationService, CategoriesService categoriesService) {
        this.hotelService = hotelService;
        this.locationService = locationService;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "hotel";
    }

    @GetMapping("/hoteldetails/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        model.addAttribute("hotel", hotel);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        List<Categories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        return "hoteldetails";
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

    @PostMapping("/hotel/saveHotel")
    public String saveHotel(@RequestParam(name = "location_id", defaultValue = "0") Long id,
                            @RequestParam(name = "location_city", defaultValue = "No City") String name,
                            @RequestParam(name = "location_country") BigDecimal price) {
        Location lct = locationService.getLocation(id);
        if (lct != null) {
            Hotel hotel = new Hotel();
            hotel.setName(name);
            hotel.setPrice(price);
            hotel.setLocation(lct);
            hotelService.saveHotel(hotel);
        }
        return "redirect:/hotel/";
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

    @PostMapping("/deletehotel")
    public String deleteHotel(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        if (hotel != null) {
            hotelService.deleteHotel(hotel);
        }
        return "redirect:/hotel/";
    }

    @PostMapping("/assigncategory")
    public String assignCategory(@RequestParam(name = "hotel_id") Long hotelId,
                                 @RequestParam(name = "category_id") Long categoryId) {
        Categories cat = categoriesService.getCategories(categoryId);
        if(cat!=null){
            Hotel hotel = hotelService.getHotel(hotelId);
            if(hotel!=null){
                List<Categories> categories = hotel.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();
                }
                categories.add(cat);
                hotelService.saveHotel(hotel);

                return "redirect:/hotel/hoteldetails/"+hotelId;
            }
        }
        return "redirect:/";
    }
}
