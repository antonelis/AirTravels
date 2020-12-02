package com.iths.airtravels.controller;


import com.iths.airtravels.entity.Ticket;
import com.iths.airtravels.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/findall")
    public Iterable<Ticket> findAllTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping("/id/{id}")
    public Optional<Ticket> findTicketById(@PathVariable Long id) {
        return ticketService.findTicketById(id);
    }

    @PostMapping("/create")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
    }
}
