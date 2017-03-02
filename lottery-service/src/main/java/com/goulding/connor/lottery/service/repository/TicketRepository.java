package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.model.TicketDto;
import com.goulding.connor.lottery.service.model.LineDto;

import java.util.List;

/**
 * Created by connor.
 */
public interface TicketRepository
{
    List<TicketDto> readAllTickets();

    TicketDto addTicket(List<LineDto> lines);

    TicketDto readTicket(String ticketUuid);

    TicketDto updateTicket(TicketDto updated);

}
