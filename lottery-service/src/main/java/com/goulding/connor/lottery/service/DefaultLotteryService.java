package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.LineResult;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.TicketResult;
import com.goulding.connor.lottery.service.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Connor Goulding
 */
public class DefaultLotteryService implements LotteryService {
    private final TicketRepository ticketRepository;
    private final LineGenerationService lineGenerationService;
    private final LineEvaluationService lineEvaluationService;

    public DefaultLotteryService(final TicketRepository ticketRepository, final LineGenerationService lineGenerationService,
                                 final LineEvaluationService lineEvaluationService) {
        this.ticketRepository = ticketRepository;
        this.lineGenerationService = lineGenerationService;
        this.lineEvaluationService = lineEvaluationService;
    }

    @Override
    public Ticket findTicket(String ticketUuid) {
        return ticketRepository.readTicket(ticketUuid);
    }

    @Override
    public List<Ticket> readAllTickets() {
        return ticketRepository.readAllTickets();
    }

    @Override
    public Ticket generateTicket(Integer numberOfLines) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) {
            lines.add(lineGenerationService.generateLine());
        }
        return ticketRepository.addTicket(lines);
    }

    @Override
    public TicketResult checkStatus(String ticketUuid) {
        Ticket existing = ticketRepository.readTicket(ticketUuid);
        if (existing == null) {
            return null;
        }

        Ticket checked = new Ticket(existing.getTicketUuid(), existing.getLines(), Calendar.getInstance().getTime());
        checked = ticketRepository.updateTicket(checked);

        List<LineResult> lineResults = checked.getLines().stream()
                .map(this::evaluate)
                .collect(Collectors.toList());

        return new TicketResult(ticketUuid, lineResults);
    }

    @Override
    public Ticket ammendTicket(String ticketUuid, Integer numberOfLines) {
        Ticket existing = ticketRepository.readTicket(ticketUuid);
        if (existing == null) {
            return null;
        }

        List<Line> newLines = new ArrayList<>();
        newLines.addAll(existing.getLines());

        // Only allow ammend if ticket has not already been checked
        if (existing.getCheckedTime() == null) {
            for (int i = 0; i < numberOfLines; i++) {
                newLines.add(lineGenerationService.generateLine());
            }
        }

        return ticketRepository.updateTicket(new Ticket(existing.getTicketUuid(), newLines, existing.getCheckedTime()));
    }

    private LineResult evaluate(Line line) {
        Integer evaluation = lineEvaluationService.evaluateLine(line);
        return new LineResult(line, evaluation);
    }
}