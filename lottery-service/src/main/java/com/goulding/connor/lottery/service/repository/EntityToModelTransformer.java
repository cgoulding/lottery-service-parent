package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 21/02/17.
 */
public class EntityToModelTransformer
{

    public Ticket transform(final TicketEntity ticket) {
        assert ticket != null;

        List<Line> lines = ticket.getLines().stream().map(this::transform).collect(Collectors.toList());
        return new Ticket(ticket.getTicketUuid(), lines, ticket.getCheckedTime());
    }

    private Line transform(final LineEntity line)
    {
        return new Line(line.getNumber1(), line.getNumber2(), line.getNumber3());
    }

}
