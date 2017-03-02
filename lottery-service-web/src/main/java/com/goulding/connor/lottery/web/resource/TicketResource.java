/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.resource;

import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.model.TicketDto;
import org.springframework.hateoas.ResourceSupport;

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
public class TicketResource extends ResourceSupport
{
    private final String ticketUuid;
    private final List<LineDto> lines = new ArrayList<>();
    private final Date checkedTime;

    public TicketResource(TicketDto ticketDto)
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

    public List<LineDto> getLines()
    {
        return lines;
    }

    public Date getCheckedTime()
    {
        return checkedTime;
    }
}
