package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Luggage;
import com.iths.airtravels.service.LuggageService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/luggage")
public class LuggageController {

    private LuggageService luggageService;

    public LuggageController(LuggageService luggageService) {
        this.luggageService = luggageService;
    }

    @GetMapping("/findall")
    public Iterable<Luggage> findAllLuggage(){
        return luggageService.findAllLuggage();
    }

    @GetMapping("/id/{id}")
    public Optional<Luggage> findLuggageById(@PathVariable Long id){
       return luggageService.findLuggageById(id);
    }

    @GetMapping("/findbyuser/{id}")
    public Iterable<Luggage> findAllLuggageByUserId(@PathVariable Long id){
        return  luggageService.findLuggageByUserId(id);
    }

    @PostMapping("/create")
    public Luggage createLuggage(@RequestBody Luggage luggage){
        return luggageService.createLuggage(luggage);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLuggage(@PathVariable Long id){
        luggageService.deleteLuggageById(id);
    }

    @GetMapping("/getluggagebyuser")
    Iterable<Luggage> findLuggageByUser(){
        return luggageService.findLuggageByAuthUser();
    }
}
