package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

/**
 * @author Connor Goulding
 */
public class PersistingTicketRepositoryTest {

    private TicketRepository ticketRepository;
    private TicketDao ticketDao;

    @Before
    public void setUp() {
        ticketDao = Mockito.mock(TicketDao.class);
        ticketRepository = new PersistingTicketRepository(ticketDao, new ModelToEntityTransformer(), new EntityToModelTransformer());
    }

    @Test
    public void testReadTickets() {
        TicketEntity ticketEntity = new TicketEntity(UUID.randomUUID().toString(), new ArrayList<>(), Calendar.getInstance().getTime());

        Mockito.when(ticketDao.readTickets()).thenReturn(Arrays.asList(ticketEntity));
        List<Ticket> tickets = ticketRepository.readAllTickets();
        Mockito.verify(ticketDao, Mockito.times(1)).readTickets();

        Assert.assertEquals(1, tickets.size());
        Ticket read = tickets.get(0);

        Assert.assertEquals(ticketEntity.getTicketUuid(), read.getTicketUuid());
    }

    @Test
    public void testCreateTicket() {
        Date now = Calendar.getInstance().getTime();

        TicketEntity expectedTicket = new TicketEntity(UUID.randomUUID().toString(), new ArrayList<>(), now);
        Mockito.when(ticketDao.createTicket(new ArrayList<>())).thenReturn(expectedTicket);
        Ticket added = ticketRepository.addTicket(new ArrayList<>());
        Mockito.verify(ticketDao, Mockito.times(1)).createTicket(Mockito.any());

        Assert.assertEquals(expectedTicket.getTicketUuid(), added.getTicketUuid());
        Assert.assertEquals(new ArrayList<>(), added.getLines());
        Assert.assertEquals(expectedTicket.getCheckedTime(), added.getCheckedTime());
    }

    @Test
    public void testUpdateTicket() {
        Date now = Calendar.getInstance().getTime();
        String uuid = UUID.randomUUID().toString();

        TicketEntity expectedTicket = new TicketEntity(uuid, Arrays.asList(new LineEntity("lineUuid", 0, 1, 2)), now);

        Mockito.when(ticketDao.updateTicket(Mockito.any())).thenReturn(expectedTicket);
        Ticket updated = ticketRepository.updateTicket(new Ticket(uuid, Arrays.asList(
                new Line(UUID.randomUUID().toString(), 0, 1, 2)), now));
        Mockito.verify(ticketDao, Mockito.times(1)).updateTicket(Mockito.any());

        Assert.assertEquals(expectedTicket.getTicketUuid(), updated.getTicketUuid());
    }

    @Test
    public void testReadTicket() {
        Date now = Calendar.getInstance().getTime();
        String ticketUuid = UUID.randomUUID().toString();

        TicketEntity expectedTicket = new TicketEntity(ticketUuid, Arrays.asList(new LineEntity("lineUuid", 0, 1, 2)), now);

        Mockito.when(ticketDao.readTicket(ticketUuid)).thenReturn(expectedTicket);
        Ticket read = ticketRepository.readTicket(ticketUuid);
        Mockito.verify(ticketDao, Mockito.times(1)).readTicket(ticketUuid);

        Assert.assertEquals(1, read.getLines().size());
        Assert.assertEquals(expectedTicket.getTicketUuid(), read.getTicketUuid());
    }
}
