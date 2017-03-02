/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class Ticket implements Serializable
{
    private String ticketUuid;
    private List<Line> lines;
    private Date checkedTime;

    public Ticket()
    {
    }

    public Ticket(String ticketUuid, List<Line> lines, Date checkedTime)
    {
        this.ticketUuid = ticketUuid;
        this.lines = lines;
        this.checkedTime = checkedTime;
    }

    public String getTicketUuid()
    {
        return ticketUuid;
    }

    public List<Line> getLines()
    {
        return lines;
    }

    public void setLines(List<Line> lines)
    {
        this.lines = lines;
    }

    public Date getCheckedTime()
    {
        return checkedTime;
    }
}
