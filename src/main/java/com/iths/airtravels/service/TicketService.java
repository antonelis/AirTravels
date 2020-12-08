package com.iths.airtravels.service;

import com.iths.airtravels.entity.Ticket;
import com.iths.airtravels.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> findTicketById(Long id){
        return ticketRepository.findById(id);
    }

    public Iterable<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }

    public void deleteTicketById(Long id){
        Optional<Ticket> foundTicket = ticketRepository.findById(id);
        ticketRepository.deleteById(foundTicket.get().getId());
    }

    public Iterable<Ticket> findTicketsByUserId(Long id){
        return ticketRepository.findTicketsByUserId(id);
    }
}
