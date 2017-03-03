package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

/**
 * @author Connor Goulding
 */
public class ModelToEntityTransformerTest {
    private ModelToEntityTransformer transformer;

    @Before
    public void setUp() {
        transformer = new ModelToEntityTransformer();
    }

    @Test(expected = AssertionError.class)
    public void testTransformNull() {
        transformer.transform((Ticket)null);
    }

    @Test
    public void testTransformEmpty() {
        TicketEntity ticket = transformer.transform(new Ticket(null, null, null));
        Assert.assertNotNull(ticket);
        Assert.assertNull(ticket.getTicketUuid());
        Assert.assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void testTransform() {
        Ticket ticket = new Ticket("ticketUuid",
                Arrays.asList(new Line("lineUuid", 0, 1, 2)), Calendar.getInstance().getTime());

        TicketEntity ticketEntity = transformer.transform(ticket);
        Assert.assertEquals(ticket.getTicketUuid(), ticketEntity.getTicketUuid());
        Assert.assertEquals(ticket.getCheckedTime(), ticketEntity.getCheckedTime());
        Assert.assertEquals(ticket.getLines().size(), ticketEntity.getLines().size());
    }
}
