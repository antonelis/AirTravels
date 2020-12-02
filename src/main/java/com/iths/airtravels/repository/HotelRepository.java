package com.iths.airtravels.repository;

import com.iths.airtravels.entity.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE h.location.id = :id")
    Iterable<Hotel> findHotelsByLocationId(Long id);

}