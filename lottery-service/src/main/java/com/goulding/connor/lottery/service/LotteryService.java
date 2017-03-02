package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.TicketDto;
import com.goulding.connor.lottery.service.model.TicketResultDto;

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

    List<TicketDto> readAllTickets();

    TicketDto generateTicket(Integer lines);

    TicketDto findTicket(String ticketUuid);

    TicketDto ammendTicket(String ticketUuid, Integer lines);

    TicketResultDto checkStatus(String ticketUuid);
}
