package com.iths.airtravels.repository;

import com.iths.airtravels.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.user.id = :id")
    Iterable<Ticket> findTicketsByUserId(Long id);
}
