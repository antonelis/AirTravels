package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Luggage;
import com.iths.airtravels.service.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/luggage")
public class LuggageController {

    @Autowired
    LuggageService luggageService;

    @GetMapping("/findall")
    public Iterable<Luggage> findAllLuggage(){
        return luggageService.findAllLuggage();
    }

    @GetMapping("/id/{id}")
    public Optional<Luggage> findLuggageById(@PathVariable Long id){
       return luggageService.findLuggageById(id);
    }

    @PostMapping("/create")
    public Luggage createLuggage(@RequestBody Luggage luggage){
        return luggageService.createLuggage(luggage);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLuggage(@PathVariable Long id){
        luggageService.deleteLuggageById(id);
    }
}
