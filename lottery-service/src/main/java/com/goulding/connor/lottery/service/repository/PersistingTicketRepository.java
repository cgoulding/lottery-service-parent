package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Connor Goulding
 */
public class PersistingTicketRepository implements TicketRepository {

    private final TicketDao ticketDao;
    private final ModelToEntityTransformer modelToEntityTransformer;
    private final EntityToModelTransformer entityToModelTransformer;

    public PersistingTicketRepository(final TicketDao ticketDao, final ModelToEntityTransformer modelToEntityTransformer,
                                      final EntityToModelTransformer entityToModelTransformer) {
        this.ticketDao = ticketDao;
        this.modelToEntityTransformer = modelToEntityTransformer;
        this.entityToModelTransformer = entityToModelTransformer;
    }

    /**
     * Read all tickets
     *
     * @return
     */
    @Override
    public List<Ticket> readAllTickets() {
        return ticketDao.readTickets().stream()
                .map(entityToModelTransformer::transform)
                .collect(Collectors.toList());
    }

    /**
     * Create ticket with specified number of lines
     *
     * @param ticketLines
     * @return
     */
    @Override
    public Ticket addTicket(List<Line> ticketLines) {
        List<LineEntity> lines = ticketLines.stream()
                .map(modelToEntityTransformer::transform)
                .collect(Collectors.toList());

        return entityToModelTransformer.transform(ticketDao.createTicket(lines));
    }

    /**
     * Read ticket
     *
     * @param ticketUuid
     * @return
     */
    @Override
    public Ticket readTicket(String ticketUuid) {
        TicketEntity ticket = ticketDao.readTicket(ticketUuid);
        if (ticket != null) {
            return entityToModelTransformer.transform(ticket);
        }
        return null;
    }

    /**
     * Update ticket
     *
     * @param ticket
     * @return
     */
    @Override
    public Ticket updateTicket(Ticket ticket) {
        TicketEntity entity = modelToEntityTransformer.transform(ticket);
        TicketEntity updated = ticketDao.updateTicket(entity);
        return entityToModelTransformer.transform(updated);
    }
}
