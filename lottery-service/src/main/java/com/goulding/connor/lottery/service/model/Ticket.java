package com.goulding.connor.lottery.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Connor Goulding
 */
public class Ticket {
    private final String ticketUuid;
    private final List<Line> lines = new ArrayList<>();
    private final Date checkedTime;

    /**
     * Default constructor for immutable class
     *
     * Json annotations are for the purposes of serialisation
     *
     * @param ticketUuid
     * @param lines
     * @param checkedTime
     */
    @JsonCreator
    public Ticket(@JsonProperty("ticketUuid") String ticketUuid, @JsonProperty("lines") List<Line> lines,
                  @JsonProperty("checkedTime") Date checkedTime) {
        this.ticketUuid = ticketUuid;
        this.checkedTime = checkedTime;
        if (lines != null) {
            this.lines.addAll(lines);
        }
    }

    public String getTicketUuid() {
        return ticketUuid;
    }

    public List<Line> getLines() {
        return lines;
    }

    public Date getCheckedTime() {
        return checkedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Ticket ticket = (Ticket) o;

        if (ticketUuid != null ? !ticketUuid.equals(ticket.ticketUuid) : ticket.ticketUuid != null)
            return false;
        if (lines != null ? !lines.equals(ticket.lines) : ticket.lines != null)
            return false;
        return checkedTime != null ? checkedTime.equals(ticket.checkedTime) : ticket.checkedTime == null;

    }

    @Override
    public int hashCode() {
        int result = ticketUuid != null ? ticketUuid.hashCode() : 0;
        result = 31 * result + (lines != null ? lines.hashCode() : 0);
        result = 31 * result + (checkedTime != null ? checkedTime.hashCode() : 0);
        return result;
    }
}
