package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Connor Goulding
 */
public class ModelToEntityTransformer {

    /**
     * Transform from model to entity
     *
     * @param ticket
     * @return
     */
    public TicketEntity transform(Ticket ticket) {
        assert ticket != null;

        List<LineEntity> lines = null;
        if (ticket.getLines() != null) {
            lines = ticket.getLines().stream().map(this::transform).collect(Collectors.toList());
        }
        return new TicketEntity(ticket.getTicketUuid(), lines, ticket.getCheckedTime());
    }

    /**
     * Transform from model to entity
     *
     * @param line
     * @return
     */
    public LineEntity transform(Line line) {
        assert line != null;

        return new LineEntity(UUID.randomUUID().toString(), line.getNumber1(), line.getNumber2(), line.getNumber3());
    }
}
