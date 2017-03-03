package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.TicketResult;

import java.util.List;

/**
 * @author Connor Goulding
 */
public interface LotteryService {

    /**
     * Read all tickets
     *
     * @return
     */
    List<Ticket> readAllTickets();

    /**
     * Generate a ticket with the specified number of lines
     *
     * @param lines
     * @return
     */
    Ticket generateTicket(Integer lines);

    /**
     * Find a ticket for the specified ticketUuid
     *
     * @param ticketUuid
     * @return
     */
    Ticket findTicket(String ticketUuid);

    /**
     * Ammend the ticket with the specified number of lines
     *
     * @param ticketUuid
     * @param lines
     * @return
     */
    Ticket ammendTicket(String ticketUuid, Integer lines);

    /**
     * Check the ticket status. Once this is performed, no more lines can be added
     *
     * @param ticketUuid
     * @return
     */
    TicketResult checkStatus(String ticketUuid);
}
