package com.goulding.connor.lottery.service.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Connor Goulding
 */
public class TicketEntity implements Serializable {
    private String ticketUuid;
    private List<LineEntity> lines;
    private Date checkedTime;

    public TicketEntity() {
    }

    public TicketEntity(String ticketUuid, List<LineEntity> lines, Date checkedTime) {
        this.ticketUuid = ticketUuid;
        this.lines = lines;
        this.checkedTime = checkedTime;
    }

    public String getTicketUuid() {
        return ticketUuid;
    }

    public void setTicketUuid(String ticketUuid) {
        this.ticketUuid = ticketUuid;
    }

    public List<LineEntity> getLines() {
        return lines;
    }

    public void setLines(List<LineEntity> lines) {
        this.lines = lines;
    }

    public Date getCheckedTime() {
        return checkedTime;
    }

    public void setCheckedTime(Date checkedTime) {
        this.checkedTime = checkedTime;
    }
}
