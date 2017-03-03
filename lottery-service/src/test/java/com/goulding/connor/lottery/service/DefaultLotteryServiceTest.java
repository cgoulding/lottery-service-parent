package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.TicketResult;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Matchers.any;

/**
 * @author Connor Goulding
 */
public class DefaultLotteryServiceTest {

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
    public void setUp() {
        ticketRepository = Mockito.mock(TicketRepository.class);
        lineGenerationService = Mockito.mock(LineGenerationService.class);
        lineEvaluationService = Mockito.mock(LineEvaluationService.class);
        lotteryService = new DefaultLotteryService(ticketRepository, lineGenerationService, lineEvaluationService);
    }

    @Test
    public void testGenerateTicket() {
        Ticket expected = new Ticket(uuid(), Arrays.asList(new Line(uuid(), 0, 0, 0)), null);
        Mockito.when(ticketRepository.addTicket(Mockito.any())).thenReturn(expected);
        Ticket generated = lotteryService.generateTicket(1);
        Mockito.verify(ticketRepository, Mockito.times(1)).addTicket(Mockito.any());

        Assert.assertEquals(expected.getTicketUuid(), generated.getTicketUuid());
    }

    @Test
    public void testAmmendTicket() {
        Ticket original = new Ticket(uuid(), Arrays.asList(new Line(uuid(), 0, 0, 0)), null);
        Ticket updated = new Ticket(original.getTicketUuid(), Arrays.asList(new Line(uuid(), 0, 0, 0),
                new Line(uuid(), 1, 1, 1)), null);

        Mockito.when(ticketRepository.readTicket(original.getTicketUuid())).thenReturn(original);
        Mockito.when(ticketRepository.updateTicket(any())).thenReturn(updated);

        Assert.assertEquals("Original ticket has one line", 1, original.getLines().size());
        Ticket ammended = lotteryService.ammendTicket(original.getTicketUuid(), 1);

        Mockito.verify(ticketRepository, Mockito.times(1)).readTicket(original.getTicketUuid());
        Mockito.verify(ticketRepository, Mockito.times(1)).updateTicket(any());

        Assert.assertSame(updated, ammended);
        Assert.assertEquals("Ammended ticket has additional line", 2, ammended.getLines().size());
    }

    @Test
    public void testCheckStatus() {
        Date expectedCheckedDate = Calendar.getInstance().getTime();

        Ticket notChecked = new Ticket(uuid(), Arrays.asList(new Line(uuid(), 0, 0, 0)), null);
        Ticket checked = new Ticket(notChecked.getTicketUuid(), Arrays.asList(new Line(uuid(), 0, 0, 0)),
                expectedCheckedDate);

        Mockito.when(ticketRepository.readTicket(notChecked.getTicketUuid())).thenReturn(notChecked);
        Mockito.when(ticketRepository.updateTicket(any())).thenReturn(checked);

        Assert.assertNull("Not checked has no checkedDate", notChecked.getCheckedTime());
        TicketResult result = lotteryService.checkStatus(notChecked.getTicketUuid());

        Mockito.verify(ticketRepository, Mockito.times(1)).readTicket(notChecked.getTicketUuid());
        Mockito.verify(ticketRepository, Mockito.times(1)).updateTicket(any());

        Assert.assertEquals(1, result.getLineResults().size());
        assertLinesEqual(result.getLineResults().get(0).getLine(), checked.getLines().get(0));
        Assert.assertEquals(result.getTicketUuid(), checked.getTicketUuid());
    }

    private void assertLinesEqual(Line line1, Line line2) {
        Assert.assertEquals(line1.getNumber1(), line2.getNumber1());
        Assert.assertEquals(line1.getNumber2(), line2.getNumber2());
        Assert.assertEquals(line1.getNumber3(), line2.getNumber3());
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }
}
