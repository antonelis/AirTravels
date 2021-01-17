package com.iths.airtravels.service;

import com.iths.airtravels.entity.User;
import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.Luggage;
import com.iths.airtravels.repository.LuggageRepository;

import java.util.Optional;

@Service
public class LuggageService {

    private LuggageRepository luggageRepository;
   // private UserService userService;

    public LuggageService(LuggageRepository luggageRepository){//, UserService userService) {
        this.luggageRepository = luggageRepository;
       // this.userService = userService;
    }

    public Luggage createLuggage(Luggage luggage){
      //  luggage.setUser(userService.getAuthenticatedUser());
        return luggageRepository.save(luggage);
    }

    public Optional<Luggage> findLuggageById(Long id){
        return luggageRepository.findById(id);
    }

    public Iterable<Luggage> findAllLuggage(){
        return luggageRepository.findAll();
    }

    public void deleteLuggageById(Long id){
        Optional<Luggage> foundLuggage = luggageRepository.findById(id);
        luggageRepository.deleteById(foundLuggage.get().getId());
    }

    public Iterable<Luggage> findLuggageByUserId(Long id){
        return luggageRepository.findLuggageByUserId(id);
    }

    public Iterable<Luggage> findLuggageByAuthUser(){
        Iterable<Luggage> luggageByUser = luggageRepository.findLuggageByAuthUser();
        return luggageByUser;
    }
}
