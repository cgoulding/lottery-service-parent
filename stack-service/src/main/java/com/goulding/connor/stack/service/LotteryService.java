package com.goulding.connor.stack.service;

import com.goulding.connor.stack.service.model.LineDto;
import com.goulding.connor.stack.service.model.ResultDto;
import com.goulding.connor.stack.service.model.TicketDto;

import java.util.Collection;

/**
 * Created by root on 20/02/17.
 */
public interface LotteryService
{

    ResultDto evaluateTicket(TicketDto ticket);

}
