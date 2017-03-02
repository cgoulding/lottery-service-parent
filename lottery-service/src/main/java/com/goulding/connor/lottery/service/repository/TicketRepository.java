package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.Line;

import java.util.List;

/**
 * Created by connor.
 */
public interface TicketRepository
{
    List<Ticket> readAllTickets();

    Ticket addTicket(List<Line> lines);

    Ticket readTicket(String ticketUuid);

    Ticket updateTicket(Ticket updated);

}
