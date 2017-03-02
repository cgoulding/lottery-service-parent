/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.model;

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
public class TicketResult
{
    private final String ticketUuid;
    private final List<LineResult> lineResults = new ArrayList<>();

    public TicketResult(String ticketUuid, List<LineResult> lineResults)
    {
        this.ticketUuid = ticketUuid;
        if (lineResults != null)
        {
            this.lineResults.addAll(lineResults);
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
