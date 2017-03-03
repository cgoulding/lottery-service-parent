package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.LineResult;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.TicketResult;
import com.goulding.connor.lottery.service.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Connor Goulding
 */
public class DefaultLotteryService implements LotteryService {
    private static final LineResultComparator LINE_RESULT_COMPARATOR = new LineResultComparator();

    private final TicketRepository ticketRepository;
    private final LineGenerationService lineGenerationService;
    private final LineEvaluationService lineEvaluationService;

    /**
     * Default constructor
     *
     * @param ticketRepository
     * @param lineGenerationService
     * @param lineEvaluationService
     */
    public DefaultLotteryService(final TicketRepository ticketRepository, final LineGenerationService lineGenerationService,
                                 final LineEvaluationService lineEvaluationService) {
        this.ticketRepository = ticketRepository;
        this.lineGenerationService = lineGenerationService;
        this.lineEvaluationService = lineEvaluationService;
    }

    /**
     * Find ticket
     *
     * @param ticketUuid
     * @return
     */
    @Override
    public Ticket findTicket(String ticketUuid) {
        return ticketRepository.readTicket(ticketUuid);
    }

    /**
     * Read all tickets
     *
     * @return
     */
    @Override
    public List<Ticket> readAllTickets() {
        return ticketRepository.readAllTickets();
    }

    /**
     * Generate ticket with specified number of line
     *
     * @param numberOfLines
     * @return
     */
    @Override
    public Ticket generateTicket(Integer numberOfLines) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) {
            lines.add(lineGenerationService.generateLine());
        }
        return ticketRepository.addTicket(lines);
    }

    /**
     * Check status for specified ticket
     *
     * @param ticketUuid
     * @return
     */
    @Override
    public TicketResult checkStatus(String ticketUuid) {
        Ticket ticket = ticketRepository.readTicket(ticketUuid);
        if (ticket == null) {
            return null;
        }

        // There is only need to update the checked date if it wasn't checked before. This reduces IO churn
        // and keeps operation idempotent
        if (ticket.getCheckedTime() == null)
        {
            Ticket checked = new Ticket(ticket.getTicketUuid(), ticket.getLines(), Calendar.getInstance().getTime());
            ticket = ticketRepository.updateTicket(checked);
        }

        List<LineResult> lineResults = ticket.getLines().stream()
                .map(this::evaluate)
                .sorted(LINE_RESULT_COMPARATOR)
                .collect(Collectors.toList());

        return new TicketResult(ticketUuid, lineResults);
    }

    /**
     * Ammend ticket with specific number of lines
     *
     * @param ticketUuid
     * @param numberOfLines
     * @return
     */
    @Override
    public Ticket ammendTicket(String ticketUuid, Integer numberOfLines) {
        Ticket existing = ticketRepository.readTicket(ticketUuid);
        if (existing == null) {
            return null;
        }

        List<Line> newLines = new ArrayList<>();
        newLines.addAll(existing.getLines());

        // Only allow amend if ticket has not already been checked
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

    /**
     * Compare line results based on the actual evaluation result
     */
    private static class LineResultComparator implements Comparator<LineResult> {
        @Override
        public int compare(LineResult o1, LineResult o2) {
            // Return all the highest results first
            return o2.getResult().compareTo(o1.getResult());
        }
    }
}