/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.resource;

import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import org.springframework.hateoas.ResourceSupport;

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
public class TicketResource extends ResourceSupport
{
    private final String ticketUuid;
    private final List<Line> lines = new ArrayList<>();
    private final Date checkedTime;

    public TicketResource(Ticket ticketDto)
    {
        this.ticketUuid = ticketDto.getTicketUuid();
        this.checkedTime = ticketDto.getCheckedTime();
        if (ticketDto.getLines() != null)
        {
            lines.addAll(ticketDto.getLines());
        }
    }

    public String getTicketUuid()
    {
        return ticketUuid;
    }

    public List<Line> getLines()
    {
        return lines;
    }

    public Date getCheckedTime()
    {
        return checkedTime;
    }
}
