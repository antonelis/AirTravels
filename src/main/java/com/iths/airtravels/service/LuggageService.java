package com.iths.airtravels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.Luggage;
import com.iths.airtravels.repository.LuggageRepository;

import java.util.Optional;

@Service
public class LuggageService {

    @Autowired
    LuggageRepository luggageRepository;

    public Luggage createLuggage(Luggage luggage){
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

}
