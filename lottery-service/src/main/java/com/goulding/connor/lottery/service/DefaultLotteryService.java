package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.model.LineResultDto;
import com.goulding.connor.lottery.service.model.TicketDto;
import com.goulding.connor.lottery.service.model.TicketResultDto;
import com.goulding.connor.lottery.service.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by connor.
 */
public class DefaultLotteryService implements LotteryService
{
    private final TicketRepository      ticketRepository;
    private final LineGenerationService lineGenerationService;
    private final LineEvaluationService lineEvaluationService;

    public DefaultLotteryService(final TicketRepository ticketRepository, final LineGenerationService lineGenerationService,
            final LineEvaluationService lineEvaluationService)
    {
        this.ticketRepository = ticketRepository;
        this.lineGenerationService = lineGenerationService;
        this.lineEvaluationService = lineEvaluationService;
    }

    @Override
    public TicketDto findTicket(String ticketUuid)
    {
        return ticketRepository.readTicket(ticketUuid);
    }

    @Override
    public List<TicketDto> readAllTickets()
    {
        return ticketRepository.readAllTickets();
    }

    @Override
    public TicketDto generateTicket(Integer numberOfLines)
    {
        List<LineDto> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++)
        {
            lines.add(lineGenerationService.generateLine());
        }
        return ticketRepository.addTicket(lines);
    }

    @Override
    public TicketResultDto checkStatus(String ticketUuid)
    {
        TicketDto existing = ticketRepository.readTicket(ticketUuid);
        if (existing == null)
        {
            return null;
        }

        TicketDto checked = new TicketDto(existing.getTicketUuid(), existing.getLines(), Calendar.getInstance().getTime());
        ticketRepository.updateTicket(checked);

        List<LineResultDto> lineResults = existing.getLines().stream()
                .map(this::evaluate)
                .collect(Collectors.toList());

        return new TicketResultDto(ticketUuid, lineResults);
    }

    @Override
    public TicketDto ammendTicket(String ticketUuid, Integer numberOfLines)
    {
        TicketDto existing = ticketRepository.readTicket(ticketUuid);
        if (existing == null)
        {
            return null;
        }

        List<LineDto> newLines = new ArrayList<>();
        newLines.addAll(existing.getLines());

        // Only allow ammend if ticket has not already been checked
        if (existing.getCheckedTime() == null) {
            for (int i = 0; i < numberOfLines; i++)
            {
                newLines.add(lineGenerationService.generateLine());
            }
        }

        return ticketRepository.updateTicket(new TicketDto(existing.getTicketUuid(), newLines, null));
    }

    private LineResultDto evaluate(LineDto line)
    {
        Integer evaluation = lineEvaluationService.evaluateLine(line);
        return new LineResultDto(line, evaluation);
    }
}