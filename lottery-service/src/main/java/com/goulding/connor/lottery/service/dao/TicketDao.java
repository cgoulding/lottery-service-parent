package com.goulding.connor.lottery.service.dao;

import com.goulding.connor.lottery.service.entity.Line;
import com.goulding.connor.lottery.service.entity.Ticket;

import java.util.List;

/**
 * Created by connor.
 */
public interface TicketDao
{
    List<Ticket> readTickets();

    Ticket createTicket(List<Line> lines);

    Ticket readTicket(String ticketUuid);

    Ticket updateTicket(Ticket ticket);
}
