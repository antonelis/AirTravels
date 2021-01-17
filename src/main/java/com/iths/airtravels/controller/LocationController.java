package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Location;
import com.iths.airtravels.entity.Users;
import com.iths.airtravels.service.IUsersService;
import com.iths.airtravels.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/location/")
public class LocationController {

    private final IUsersService usersService;
    private final LocationService locationService;

    public LocationController(IUsersService usersService, LocationService locationService) {
        this.usersService = usersService;
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currentUser", getUserData());
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "index";
    }

    @GetMapping("/findall")
    public List<Location> findAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/saveLocation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String saveLocation(@RequestParam(name = "id", defaultValue = "0") Long id,
                               @RequestParam(name = "location_city", defaultValue = "No City") String city,
                               @RequestParam(name = "location_country") String country) {
        Location location = locationService.getLocation(id);
        if(location!=null) {
           location.setCity(city);
           location.setCountry(country);
           locationService.saveLocation(location);
        }
        return "redirect:/location/";
    }
    @GetMapping("/editlocation/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String editLocation(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getUserData());
        Location location = locationService.getLocation(id);
        model.addAttribute("location", location);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);


        return "editlocation";
    }

    @PostMapping("/deletelocation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String deleteLocation(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Location location = locationService.getLocation(id);
        if(location!=null) {
            locationService.deleteLocation(location);
        }
        return "redirect:/location/";
    }

    @GetMapping("/id/{id}")
    public Location findLocationById(@PathVariable Long id) {
        return locationService.getLocation(id);
    }

    @PostMapping("/addlocation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String createLocation(@RequestParam(name = "location_city", defaultValue = "No City") String city,
                                 @RequestParam(name = "location_country") String country) {
        locationService.addLocation(new Location(city, country));

        return "redirect:/location/";
    }

    @GetMapping("/addlocation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String addLocation(Model model){
        model.addAttribute("currentUser", getUserData());
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "addlocation";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getUserData());
        Location location = locationService.getLocation(id);
        model.addAttribute("location", location);
        return "details";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "about";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = usersService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
