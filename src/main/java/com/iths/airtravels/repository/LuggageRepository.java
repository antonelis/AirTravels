package com.iths.airtravels.repository;

import com.iths.airtravels.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LuggageRepository extends JpaRepository<Luggage, Long> {

    @Query("SELECT l FROM Luggage l WHERE l.user.id = :id")
    Iterable<Luggage> findLuggageByUserId(Long id);

    @Query("SELECT l FROM Luggage l WHERE l.user.username = :#{ principal?.username }")
    List<Luggage> findLuggageByAuthUser();
}
