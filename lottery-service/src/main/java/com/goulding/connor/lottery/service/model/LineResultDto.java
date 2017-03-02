/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.model;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class LineResultDto
{
    private final LineDto line;
    private final Integer result;

    public LineResultDto(LineDto line, Integer result)
    {
        this.line = line;
        this.result = result;
    }

    public Integer getResult()
    {
        return result;
    }

    public LineDto getLine()
    {
        return line;
    }
}
