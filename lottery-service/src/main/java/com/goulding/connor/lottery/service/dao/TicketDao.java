package com.goulding.connor.lottery.service.dao;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;

import java.util.List;

/**
 * Created by connor.
 */
public interface TicketDao
{
    List<TicketEntity> readTickets();

    TicketEntity createTicket(List<LineEntity> lines);

    TicketEntity readTicket(String ticketUuid);

    TicketEntity updateTicket(TicketEntity ticket);
}
