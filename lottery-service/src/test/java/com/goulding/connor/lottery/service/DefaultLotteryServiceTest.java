/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.model.TicketDto;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.UUID;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class DefaultLotteryServiceTest
{
    /*
    You have a series of lines on a ticket with 3 numbers, each of which has a value of 0, 1, or 2.
    For each ticket if the sum of the values is 2, the result is 10.
    Otherwise if they are all the same, the result is 5.
    Otherwise so long as both 2nd and 3rd numbers are different from the 1st, the result is 1.
    Otherwise the result is 0.
     */
    private LotteryService lotteryService;
    private TicketRepository ticketRepository;
    private LineGenerationService lineGenerationService;
    private LineEvaluationService lineEvaluationService;

    @Before
    public void setUp()
    {
        ticketRepository = Mockito.mock(TicketRepository.class);
        lineGenerationService = Mockito.mock(LineGenerationService.class);
        lineEvaluationService = Mockito.mock(LineEvaluationService.class);
        lotteryService = new DefaultLotteryService(ticketRepository, lineGenerationService, lineEvaluationService);
    }

    @Test
    public void testGenerateTicket()
    {
        TicketDto ticketDto = new TicketDto(UUID.randomUUID().toString(), Arrays.asList(new LineDto(0, 0, 0)), null);
        Mockito.when(ticketRepository.addTicket(Mockito.any())).thenReturn(ticketDto);
        TicketDto generatedTicket = lotteryService.generateTicket(1);
        Assert.assertEquals(ticketDto.getTicketUuid(), generatedTicket.getTicketUuid());
    }

    @Test
    public void testAmmendTicket()
    {
        TicketDto ticketDto = new TicketDto(UUID.randomUUID().toString(), Arrays.asList(new LineDto(0, 0, 0)), null);
        Mockito.when(ticketRepository.readTicket(ticketDto.getTicketUuid())).thenReturn(ticketDto);
        TicketDto generatedTicket = lotteryService.ammendTicket(ticketDto.getTicketUuid(), 1);
        Assert.assertEquals(ticketDto.getTicketUuid(), generatedTicket.getTicketUuid());
    }

    @Test
    public void testCheckStatus()
    {
    }
}
