package com.goulding.connor.lottery.service.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Connor Goulding
 */
@Entity
@Table(name = "LINE")
public class LineEntity implements Serializable {

    @Id
    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "NUMBER_ONE")
    private Integer number1;

    @Column(name = "NUMBER_TWO")
    private Integer number2;

    @Column(name = "NUMBER_THREE")
    private Integer number3;

    @ManyToOne
    @JoinColumn(name = "TICKET_UUID")
    private TicketEntity ticket;

    public LineEntity() {
    }

    public LineEntity(String lineUuid, Integer number1, Integer number2, Integer number3) {
        this.lineUuid = lineUuid;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public String getLineUuid() {
        return lineUuid;
    }

    public void setLineUuid(String lineUuid) {
        this.lineUuid = lineUuid;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public Integer getNumber3() {
        return number3;
    }

    public void setNumber3(Integer number3) {
        this.number3 = number3;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
