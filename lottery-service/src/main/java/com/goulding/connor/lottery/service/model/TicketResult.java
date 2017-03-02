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
public class TicketResultDto
{
    private final String ticketUuid;
    private final List<LineResultDto> lineResults = new ArrayList<>();

    public TicketResultDto(String ticketUuid, List<LineResultDto> lineResults)
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

    public List<LineResultDto> getLineResults()
    {
        return lineResults;
    }
}
