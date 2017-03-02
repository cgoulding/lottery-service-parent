package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.entity.Line;
import com.goulding.connor.lottery.service.entity.Ticket;
import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.model.TicketDto;

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
    public List<TicketDto> readAllTickets()
    {
        return ticketDao.readTickets().stream()
                .map(entityToModelTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto addTicket(List<LineDto> dtos)
    {
        List<Line> lines = dtos.stream()
                .map(modelToEntityTransformer::transform)
                .collect(Collectors.toList());

        return entityToModelTransformer.transform(ticketDao.createTicket(lines));
    }

    @Override
    public TicketDto readTicket(String ticketUuid)
    {
        Ticket ticket = ticketDao.readTicket(ticketUuid);
        if (ticket != null)
        {
            return entityToModelTransformer.transform(ticket);
        }
        return null;
    }

    @Override
    public TicketDto updateTicket(TicketDto dto)
    {
        Ticket entity = modelToEntityTransformer.transform(dto);
        Ticket updated = ticketDao.updateTicket(entity);
        return entityToModelTransformer.transform(updated);
    }
}
