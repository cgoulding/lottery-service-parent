/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.entity;

import java.io.Serializable;
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
public class TicketEntity implements Serializable
{
    private String           ticketUuid;
    private List<LineEntity> lines;
    private Date             checkedTime;

    public TicketEntity()
    {
    }

    public TicketEntity(String ticketUuid, List<LineEntity> lines, Date checkedTime)
    {
        this.ticketUuid = ticketUuid;
        this.lines = lines;
        this.checkedTime = checkedTime;
    }

    public String getTicketUuid()
    {
        return ticketUuid;
    }

    public List<LineEntity> getLines()
    {
        return lines;
    }

    public void setLines(List<LineEntity> lines)
    {
        this.lines = lines;
    }

    public Date getCheckedTime()
    {
        return checkedTime;
    }
}
