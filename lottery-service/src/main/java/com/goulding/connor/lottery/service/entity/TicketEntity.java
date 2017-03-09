package com.goulding.connor.lottery.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Connor Goulding
 */
@Entity
@Table(name = "TICKET")
public class TicketEntity implements Serializable {

    @Id
    @Column(name = "TICKET_UUID")
    private String ticketUuid;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineEntity> lines;

    @Column(name = "CHECKED_TIME")
    private Date checkedTime;

    public TicketEntity() {
    }

    public TicketEntity(String ticketUuid, List<LineEntity> lines, Date checkedTime) {
        this.ticketUuid = ticketUuid;
        if (lines != null) {
            lines.forEach(this::addLine);
        }
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

    public void addLine(LineEntity line) {
        if (lines == null) {
            this.lines = new ArrayList<>();
        }

        if (line != null) {
            line.setTicket(this);
            lines.add(line);
        }
    }
}
