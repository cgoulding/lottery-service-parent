package com.goulding.connor.lottery.service.dao;

import com.goulding.connor.lottery.service.entity.Line;
import com.goulding.connor.lottery.service.entity.Ticket;
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
    public void testCreateTicket() {
        Ticket ticket = ticketDao.createTicket(null);
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void testCreateNullTicket() {
        Ticket ticket = ticketDao.createTicket(null);
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void testCreateEmptyTicket() {
        Ticket ticket = ticketDao.createTicket(new ArrayList<>());
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void testCreateSingleLineTicket() {
        Ticket ticket = ticketDao.createTicket(Arrays.asList(new Line(UUID.randomUUID().toString(), 0, 1, 2)));
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(1, ticket.getLines().size());
    }

    @Test
    public void testCreateMultiLineTicket() {
        Ticket ticket = ticketDao.createTicket(Arrays.asList(
                new Line(UUID.randomUUID().toString(), 0, 1, 2),
                new Line(UUID.randomUUID().toString(), 0, 1, 2)));
        Assert.assertNotNull(ticket.getTicketUuid());
        Assert.assertEquals(2, ticket.getLines().size());
    }

    @Test
    public void testReadTicketNullTicketUuid() {
        Ticket ticket = ticketDao.readTicket(null);
        Assert.assertNull(ticket);
    }

    @Test
    public void testReadTicketMissingTicketUuid() {
        Ticket ticket = ticketDao.readTicket("asdf");
        Assert.assertNull(ticket);
    }

    @Test
    public void testReadTicketValidUuid() {
        Ticket created = ticketDao.createTicket(Arrays.asList(
                new Line(UUID.randomUUID().toString(), 0, 1, 2),
                new Line(UUID.randomUUID().toString(), 0, 1, 2)));

        Ticket read = ticketDao.readTicket(created.getTicketUuid());
        Assert.assertNotNull(read);
    }

    @Test(expected = AssertionError.class)
    public void testUpdateTicketNullTicket() {
        ticketDao.updateTicket(null);
    }

    @Test
    public void testUpdateTicketValidUuid() {
        Ticket created = ticketDao.createTicket(Arrays.asList(
                new Line(UUID.randomUUID().toString(), 0, 1, 2),
                new Line(UUID.randomUUID().toString(), 0, 1, 2)));

        Ticket updated = new Ticket(created.getTicketUuid(), Arrays.asList(
                new Line(UUID.randomUUID().toString(), 1, 1, 1),
                new Line(UUID.randomUUID().toString(), 2, 2, 2)), null);

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