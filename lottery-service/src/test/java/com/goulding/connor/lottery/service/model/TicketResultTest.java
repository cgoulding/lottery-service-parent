package com.goulding.connor.lottery.service.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author Connor Goulding
 */
public class TicketResultTest {
    @Test
    public void testNulls() {
        TicketResult ticketResult = new TicketResult(null, null);
        Assert.assertNull(ticketResult.getTicketUuid());
        Assert.assertEquals("Line results are not to be null to protect consumers", 0, ticketResult.getLineResults().size());
    }

    @Test
    public void testNotNulls() {
        String ticketUuid = UUID.randomUUID().toString();
        LineResult lineResult = new LineResult(new Line(0, 1, 2), 9);
        TicketResult ticketResult = new TicketResult(ticketUuid, Arrays.asList(lineResult));
        Assert.assertEquals(ticketUuid, ticketResult.getTicketUuid());
        Assert.assertEquals(1, ticketResult.getLineResults().size());
    }
}
