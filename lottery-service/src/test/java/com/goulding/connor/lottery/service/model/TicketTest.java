package com.goulding.connor.lottery.service.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Connor Goulding
 */
public class TicketTest {
    @Test
    public void testNulls() {
        Ticket ticket = new Ticket(null, null, null);
        Assert.assertNull(ticket.getTicketUuid());
        Assert.assertEquals("Line are not to be null to protect consumers", 0, ticket.getLines().size());
        Assert.assertNull(ticket.getCheckedTime());
    }

    @Test
    public void testNotNulls() {
        String expectedTicketUuid = UUID.randomUUID().toString();
        Date expectedCheckedDate = Calendar.getInstance().getTime();
        Line expectedLine = new Line(uuid(), 0, 1, 2);

        Ticket ticket = new Ticket(expectedTicketUuid, Arrays.asList(expectedLine), expectedCheckedDate);
        Assert.assertSame(expectedTicketUuid, ticket.getTicketUuid());
        Assert.assertSame(expectedCheckedDate, ticket.getCheckedTime());
        Assert.assertEquals(1, ticket.getLines().size());
        Assert.assertSame(expectedLine, ticket.getLines().get(0));
    }

    @Test
    public void testEquals() {
        Date now = Calendar.getInstance().getTime();
        Assert.assertTrue(new Ticket("uuid1", new ArrayList<>(), now).equals(
                new Ticket("uuid1", new ArrayList<>(), now))); // Random equals check
        Assert.assertFalse(new Ticket("uuid1", new ArrayList<>(), now).equals(
                new Ticket("uuid2", new ArrayList<>(), now))); // Random not equals check
    }

    @Test
    public void testHashCode() {
        Date now = Calendar.getInstance().getTime();
        Assert.assertTrue(new Ticket("uuid1", new ArrayList<>(), now).hashCode() ==
                new Ticket("uuid1", new ArrayList<>(), now).hashCode()); // Random equals check
        Assert.assertFalse(new Ticket("uuid1", new ArrayList<>(), now).hashCode() ==
                new Ticket("uuid2", new ArrayList<>(), now).hashCode());
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }
}
