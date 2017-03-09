package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;

import java.util.List;

/**
 * @author Connor Goulding
 */
public interface TicketRepository {

    /**
     * Read all tickets
     *
     * @return
     */
    List<Ticket> readAllTickets();

    /**
     * Add ticket with specified lines
     * @param lines
     * @return
     */
    Ticket addTicket(List<Line> lines);

    /**
     * Read ticket for specified ticketUuid
     *
     * @param ticketUuid
     * @return
     */
    Ticket readTicket(String ticketUuid);

    /**
     * Update specified ticket
     *
     * @param updated
     * @return
     */
    Ticket updateTicket(Ticket updated);

}
