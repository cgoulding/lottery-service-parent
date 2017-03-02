package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.TicketResult;

import java.util.List;

/**
 * Created by root on 20/02/17.
 */
public interface LotteryService
{
    /*
        Implement a REST interface to generate a ticket with n lines.
        Additionally we will need to have the ability to check the status of lines on a ticket.
        We would like the lines sorted into outcomes before being returned.
        It should be possible for a ticket to be amended with n additional lines.
        Once the status of a ticket has been checked it should not be posssible to amend.
         */

    List<Ticket> readAllTickets();

    Ticket generateTicket(Integer lines);

    Ticket findTicket(String ticketUuid);

    Ticket ammendTicket(String ticketUuid, Integer lines);

    TicketResult checkStatus(String ticketUuid);
}
