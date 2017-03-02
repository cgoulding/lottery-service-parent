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
public class LineResult
{
    private final Line    line;
    private final Integer result;

    public LineResult(Line line, Integer result)
    {
        this.line = line;
        this.result = result;
    }

    public Integer getResult()
    {
        return result;
    }

    public Line getLine()
    {
        return line;
    }
}
