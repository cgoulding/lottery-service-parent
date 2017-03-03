package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

/**
 * @author Connor Goulding
 */
public class EntityToModelTransformerTest {

    private EntityToModelTransformer transformer;

    @Before
    public void setUp() {
        transformer = new EntityToModelTransformer();
    }

    @Test(expected = AssertionError.class)
    public void testTransformNull() {
        transformer.transform(null);
    }

    @Test
    public void testTransformEmpty() {
        Ticket ticket = transformer.transform(new TicketEntity());
        Assert.assertNotNull(ticket);
        Assert.assertNull(ticket.getTicketUuid());
        Assert.assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void testTransform() {
        TicketEntity ticketEntity = new TicketEntity("ticketUuid",
                Arrays.asList(new LineEntity("lineUuid", 0, 1, 2)), Calendar.getInstance().getTime());

        Ticket ticket = transformer.transform(ticketEntity);
        Assert.assertEquals(ticketEntity.getTicketUuid(), ticket.getTicketUuid());
        Assert.assertEquals(ticketEntity.getCheckedTime(), ticket.getCheckedTime());
        Assert.assertEquals(ticketEntity.getLines().size(), ticket.getLines().size());
    }
}
