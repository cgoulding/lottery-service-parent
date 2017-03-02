/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Matchers.any;

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
        Ticket expected = new Ticket(UUID.randomUUID().toString(), Arrays.asList(new Line(0, 0, 0)), null);
        Mockito.when(ticketRepository.addTicket(any())).thenReturn(expected);
        Ticket generated = lotteryService.generateTicket(1);
        Assert.assertEquals(expected.getTicketUuid(), generated.getTicketUuid());
    }

    @Test
    public void testAmmendTicket()
    {
        Ticket original = new Ticket(UUID.randomUUID().toString(), Arrays.asList(new Line(0, 0, 0)), null);
        Mockito.when(ticketRepository.readTicket(original.getTicketUuid())).thenReturn(original);

        Ticket updated = new Ticket(original.getTicketUuid(), Arrays.asList(new Line(0, 0, 0), new Line(1, 1, 1)), null);
        Mockito.when(ticketRepository.updateTicket(any())).thenReturn(updated);

        Assert.assertEquals("Original ticket has one line", 1, original.getLines().size());

        Ticket ammended = lotteryService.ammendTicket(original.getTicketUuid(), 1);
        Assert.assertSame(updated, ammended);
        Assert.assertEquals("Ammended ticket has additional line", 2, ammended.getLines().size());
    }

    @Test
    public void testCheckStatus()
    {
    }
}
