package com.goulding.connor.lottery.service.repository;

import com.goulding.connor.lottery.service.entity.Line;
import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.model.TicketDto;
import com.goulding.connor.lottery.service.entity.Ticket;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by connor.
 */
public class ModelToEntityTransformer
{
    public Ticket transform(TicketDto dto) {
        List<Line> lines = dto.getLines().stream().map(this::transform).collect(Collectors.toList());
        return new Ticket(dto.getTicketUuid(), lines, dto.getCheckedTime());
    }

    public Line transform(LineDto dto) {
        return new Line(UUID.randomUUID().toString(), dto.getNumber1(), dto.getNumber2(), dto.getNumber3());
    }
}
