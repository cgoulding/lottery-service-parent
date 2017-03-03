package com.goulding.connor.lottery.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Connor Goulding
 */
public class TicketResult {
    private final String ticketUuid;
    private final List<LineResult> lineResults = new ArrayList<>();

    @JsonCreator
    public TicketResult(@JsonProperty("ticketUuid") String ticketUuid, @JsonProperty("lineResults") List<LineResult> lineResults) {
        this.ticketUuid = ticketUuid;
        if (lineResults != null) {
            this.lineResults.addAll(lineResults);
        }
    }

    public String getTicketUuid() {
        return ticketUuid;
    }

    public List<LineResult> getLineResults() {
        return lineResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TicketResult that = (TicketResult) o;

        if (ticketUuid != null ? !ticketUuid.equals(that.ticketUuid) : that.ticketUuid != null)
            return false;
        return lineResults != null ? lineResults.equals(that.lineResults) : that.lineResults == null;

    }

    @Override
    public int hashCode() {
        int result = ticketUuid != null ? ticketUuid.hashCode() : 0;
        result = 31 * result + (lineResults != null ? lineResults.hashCode() : 0);
        return result;
    }
}
