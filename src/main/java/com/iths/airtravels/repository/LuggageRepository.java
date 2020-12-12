package com.iths.airtravels.repository;

import com.iths.airtravels.entity.Luggage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuggageRepository extends CrudRepository <Luggage, Long> {

    @Query("SELECT l FROM Luggage l WHERE l.user.id = :id")
    Iterable<Luggage> findLuggageByUserId(Long id);

    @Query("SELECT l FROM Luggage l WHERE l.user.username = :#{ principal?.username }")
    List<Luggage> findLuggageByAuthUser();
}
