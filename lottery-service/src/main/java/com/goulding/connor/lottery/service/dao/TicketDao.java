package com.goulding.connor.lottery.service.dao;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;

import java.util.List;

/**
 * @author Connor Goulding
 */
public interface TicketDao {
    /**
     * Read tickets
     *
     * @return
     */
    List<TicketEntity> readTickets();

    /**
     * Create a ticket with the specified lines
     *
     * @param lines
     * @return
     */
    TicketEntity createTicket(List<LineEntity> lines);

    /**
     * Read a ticket with the specified ticketUuid
     *
     * @param ticketUuid
     * @return
     */
    TicketEntity readTicket(String ticketUuid);

    /**
     * Update the specified ticket
     *
     * @param ticket
     * @return
     */
    TicketEntity updateTicket(TicketEntity ticket);
}
