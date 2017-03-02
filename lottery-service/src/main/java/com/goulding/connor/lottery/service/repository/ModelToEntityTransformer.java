package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.entity.TicketEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by connor.
 */
public class ModelToEntityTransformer
{
    public TicketEntity transform(Ticket ticket) {
        List<LineEntity> lines = ticket.getLines().stream().map(this::transform).collect(Collectors.toList());
        return new TicketEntity(ticket.getTicketUuid(), lines, ticket.getCheckedTime());
    }

    public LineEntity transform(Line dto) {
        return new LineEntity(UUID.randomUUID().toString(), dto.getNumber1(), dto.getNumber2(), dto.getNumber3());
    }
}
