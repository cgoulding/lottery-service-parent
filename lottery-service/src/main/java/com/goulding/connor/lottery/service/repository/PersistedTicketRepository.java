package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by connor.
 */
public class PersistedTicketRepository implements TicketRepository
{

    private final TicketDao                ticketDao;
    private final ModelToEntityTransformer modelToEntityTransformer;
    private final EntityToModelTransformer entityToModelTransformer;

    public PersistedTicketRepository(final TicketDao ticketDao, final ModelToEntityTransformer modelToEntityTransformer,
                                   final EntityToModelTransformer entityToModelTransformer) {
        this.ticketDao = ticketDao;
        this.modelToEntityTransformer = modelToEntityTransformer;
        this.entityToModelTransformer = entityToModelTransformer;
    }

    @Override
    public List<Ticket> readAllTickets()
    {
        return ticketDao.readTickets().stream()
                .map(entityToModelTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket addTicket(List<Line> ticketLines)
    {
        List<LineEntity> lines = ticketLines.stream()
                .map(modelToEntityTransformer::transform)
                .collect(Collectors.toList());

        return entityToModelTransformer.transform(ticketDao.createTicket(lines));
    }

    @Override
    public Ticket readTicket(String ticketUuid)
    {
        TicketEntity ticket = ticketDao.readTicket(ticketUuid);
        if (ticket != null)
        {
            return entityToModelTransformer.transform(ticket);
        }
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket)
    {
        TicketEntity entity = modelToEntityTransformer.transform(ticket);
        TicketEntity updated = ticketDao.updateTicket(entity);
        return entityToModelTransformer.transform(updated);
    }
}
