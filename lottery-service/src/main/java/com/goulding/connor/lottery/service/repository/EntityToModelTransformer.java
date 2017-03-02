package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.Line;
import com.goulding.connor.lottery.service.entity.Ticket;
import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.model.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 21/02/17.
 */
public class EntityToModelTransformer
{

    public TicketDto transform(final Ticket ticket) {
        assert ticket != null;

        List<LineDto> lineDtos = ticket.getLines().stream().map(this::transform).collect(Collectors.toList());
        return new TicketDto(ticket.getTicketUuid(), lineDtos, ticket.getCheckedTime());
    }

    private LineDto transform(final Line line)
    {
        return new LineDto(line.getNumber1(), line.getNumber2(), line.getNumber3());
    }

}
