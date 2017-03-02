package com.goulding.connor.lottery.service.dao;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by connor.
 */
public class FileBasedTicketDaoTest
{
    private TicketDao ticketDao;

    @Before
    public void setUp() throws Exception {
        File file = createTestDirectory();
        ticketDao = new FileBasedTicketDao(file);
    }

    @Test
    public void testCreateNullTicket() {
        TicketEntity ticket = ticketDao.createTicket(null);
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertNull(ticket.getLines());
    }

    @Test
    public void testCreateEmptyTicket() {
        TicketEntity ticket = ticketDao.createTicket(new ArrayList<>());
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void testCreateSingleLineTicket() {
        TicketEntity ticket = ticketDao.createTicket(Arrays.asList(new LineEntity(UUID.randomUUID().toString(), 0, 1, 2)));
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(1, ticket.getLines().size());
    }

    @Test
    public void testCreateMultiLineTicket() {
        TicketEntity ticket = ticketDao.createTicket(Arrays.asList(
                new LineEntity(UUID.randomUUID().toString(), 0, 1, 2),
                new LineEntity(UUID.randomUUID().toString(), 0, 1, 2)));
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(2, ticket.getLines().size());
    }

    @Test
    public void testReadTicketNullTicketUuid() {
        TicketEntity ticket = ticketDao.readTicket(null);
        Assert.assertNull(ticket);
    }

    @Test
    public void testReadTicketMissingTicketUuid() {
        TicketEntity ticket = ticketDao.readTicket("asdf");
        Assert.assertNull(ticket);
    }

    @Test
    public void testReadTicketValidUuid() {
        TicketEntity created = ticketDao.createTicket(Arrays.asList(
                new LineEntity(UUID.randomUUID().toString(), 0, 1, 2),
                new LineEntity(UUID.randomUUID().toString(), 0, 1, 2)));

        TicketEntity read = ticketDao.readTicket(created.getTicketUuid());
        Assert.assertNotNull(read);
    }

    @Test(expected = AssertionError.class)
    public void testUpdateTicketNullTicket() {
        ticketDao.updateTicket(null);
    }

    @Test
    public void testUpdateTicketValidUuid() {
        TicketEntity created = ticketDao.createTicket(Arrays.asList(
                new LineEntity(UUID.randomUUID().toString(), 0, 1, 2),
                new LineEntity(UUID.randomUUID().toString(), 0, 1, 2)));

        TicketEntity updated = new TicketEntity(created.getTicketUuid(), Arrays.asList(
                new LineEntity(UUID.randomUUID().toString(), 1, 1, 1),
                new LineEntity(UUID.randomUUID().toString(), 2, 2, 2)), null);

        ticketDao.updateTicket(updated);
        updated = ticketDao.readTicket(updated.getTicketUuid());

        Assert.assertEquals(created.getTicketUuid(), updated.getTicketUuid());

        Assert.assertEquals(updated.getLines().get(0).getNumber1(), Integer.valueOf(1));
        Assert.assertEquals(updated.getLines().get(1).getNumber1(), Integer.valueOf(2));
    }

    private File createTestDirectory() throws IOException
    {
        File file = File.createTempFile("FileBasedTicketDaoTest-", null);
        file.delete();
        file.mkdirs();
        file.deleteOnExit();
        return file;
    }
}