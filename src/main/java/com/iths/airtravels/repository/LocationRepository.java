package com.iths.airtravels.repository;

import com.iths.airtravels.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Long> {

}