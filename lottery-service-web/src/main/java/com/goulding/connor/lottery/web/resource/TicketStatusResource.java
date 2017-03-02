/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.resource;

import com.goulding.connor.lottery.service.model.LineResult;
import com.goulding.connor.lottery.service.model.TicketResult;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class TicketStatusResource extends ResourceSupport
{
    private final String ticketUuid;
    private final List<LineResult> lineResults = new ArrayList<>();

    public TicketStatusResource(TicketResult ticketResult)
    {
        this.ticketUuid = ticketResult.getTicketUuid();
        if (ticketResult.getLineResults() != null)
        {
            lineResults.addAll(ticketResult.getLineResults());
        }
    }

    public String getTicketUuid()
    {
        return ticketUuid;
    }

    public List<LineResult> getLineResults()
    {
        return lineResults;
    }
}
