package com.iths.airtravels.service;

import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.Luggage;
import com.iths.airtravels.repository.LuggageRepository;

import java.util.Optional;

@Service
public class LuggageService {

    final LuggageRepository luggageRepository;

    public LuggageService(LuggageRepository luggageRepository) {
        this.luggageRepository = luggageRepository;
    }

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

    public Iterable<Luggage> findLuggageByUserId(Long id){
        return luggageRepository.findLuggageByUserId(id);
    }
}
