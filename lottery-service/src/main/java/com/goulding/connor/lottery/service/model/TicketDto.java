/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class TicketDto
{
    private final String ticketUuid;
    private final List<LineDto> lines = new ArrayList<>();
    private final Date checkedTime;

    public TicketDto(String ticketUuid, List<LineDto> lines, Date checkedTime)
    {
        this.ticketUuid = ticketUuid;
        this.checkedTime = checkedTime;
        if (lines != null)
        {
            this.lines.addAll(lines);
        }
    }

    public String getTicketUuid()
    {
        return ticketUuid;
    }

    public List<LineDto> getLines()
    {
        return lines;
    }

    public Date getCheckedTime()
    {
        return checkedTime;
    }
}
