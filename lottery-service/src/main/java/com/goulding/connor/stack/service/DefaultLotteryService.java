package com.goulding.connor.stack.service;

import com.goulding.connor.stack.service.model.ResultDto;
import com.goulding.connor.stack.service.model.TicketDto;
import com.goulding.connor.stack.service.repository.LineRepository;

/**
 * Created by connor.
 */
public class DefaultLotteryService implements LotteryService
{
    /*
    Implement a REST interface to generate a ticket with n lines.
    Additionally we will need to have the ability to check the status of lines on a ticket.
    We would like the lines sorted into outcomes before being returned.
    It should be possible for a ticket to be amended with n additional lines.
    Once the status of a ticket has been checked it should not be possible to amend.
     */
    private final LineRepository lineRepository;

    public DefaultLotteryService(final LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @Override
    public ResultDto evaluateTicket(TicketDto ticket)
    {
        return null;
    }
}
