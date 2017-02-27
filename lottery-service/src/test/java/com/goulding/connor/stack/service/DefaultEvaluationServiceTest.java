/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.stack.service;

import com.goulding.connor.stack.service.model.LineDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class DefaultEvaluationServiceTest
{
    private LineEvaluationService lineEvaluationService;

    @Before
    public void setUp()
    {
        lineEvaluationService = new DefaultLineEvaluationService();
    }

    @Test
    /*
     * You have a series of lines on a ticket with 3 numbers, each of which has a value of 0, 1, or 2.
     * For each ticket if the sum of the values is 2, the result is 10.
     * Otherwise if they are all the same, the result is 5.
     * Otherwise so long as both 2nd and 3rd numbers are different from the 1st, the result is 1.
     * Otherwise the result is 0.
     */
    public void testEvaluateLine()
    {
        Assert.assertEquals(Integer.valueOf(10), lineEvaluationService.evaluateLine(new LineDto(2, 0, 0)));
        Assert.assertEquals(Integer.valueOf(10), lineEvaluationService.evaluateLine(new LineDto(0, 2, 0)));
        Assert.assertEquals(Integer.valueOf(10), lineEvaluationService.evaluateLine(new LineDto(0, 0, 2)));

        Assert.assertEquals(Integer.valueOf(10), lineEvaluationService.evaluateLine(new LineDto(0, 1, 1)));
        Assert.assertEquals(Integer.valueOf(10), lineEvaluationService.evaluateLine(new LineDto(1, 0, 1)));
        Assert.assertEquals(Integer.valueOf(10), lineEvaluationService.evaluateLine(new LineDto(1, 1, 0)));

        Assert.assertEquals(Integer.valueOf(5), lineEvaluationService.evaluateLine(new LineDto(0, 0, 0)));
        Assert.assertEquals(Integer.valueOf(5), lineEvaluationService.evaluateLine(new LineDto(1, 1, 1)));
        Assert.assertEquals(Integer.valueOf(5), lineEvaluationService.evaluateLine(new LineDto(2, 2, 2)));

        // Assert.assertEquals(Integer.valueOf(1), evaluationService.evaluateLine(new LineDto(0, 1, 1))); Ambiguous
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(0, 1, 2)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(0, 2, 1)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(0, 2, 2)));

        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(1, 0, 0)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(1, 0, 2)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(1, 2, 0)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(1, 2, 2)));

        // Assert.assertEquals(Integer.valueOf(1), evaluationService.evaluateLine(new LineDto(2, 0, 0))); Ambiguous
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(2, 0, 1)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(2, 1, 0)));
        Assert.assertEquals(Integer.valueOf(1), lineEvaluationService.evaluateLine(new LineDto(2, 1, 1)));

        Assert.assertEquals(Integer.valueOf(0), lineEvaluationService.evaluateLine(new LineDto(2, 0, 2)));
    }
}
