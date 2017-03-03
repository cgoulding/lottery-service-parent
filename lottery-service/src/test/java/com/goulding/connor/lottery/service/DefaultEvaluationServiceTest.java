package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.goulding.connor.lottery.service.LineEvaluationService.*;

/**
 * @author Connor Goulding
 */
public class DefaultEvaluationServiceTest {
    private LineEvaluationService lineEvaluationService;

    @Before
    public void setUp() {
        lineEvaluationService = new DefaultLineEvaluationService();
    }

    @Test
    public void testEvaluateLineSum2() {
        Assert.assertEquals(Integer.valueOf(SUM_OF_TWO), lineEvaluationService.evaluateLine(new Line(uuid(), 2, 0, 0)));
        Assert.assertEquals(Integer.valueOf(SUM_OF_TWO), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 2, 0)));
        Assert.assertEquals(Integer.valueOf(SUM_OF_TWO), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 0, 2)));

        Assert.assertEquals(Integer.valueOf(SUM_OF_TWO), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 1, 1)));
        Assert.assertEquals(Integer.valueOf(SUM_OF_TWO), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 0, 1)));
        Assert.assertEquals(Integer.valueOf(SUM_OF_TWO), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 1, 0)));
    }

    @Test
    public void testEvaluateLineAllSame() {
        Assert.assertEquals(Integer.valueOf(ALL_EQUAL), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 0, 0)));
        Assert.assertEquals(Integer.valueOf(ALL_EQUAL), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 1, 1)));
        Assert.assertEquals(Integer.valueOf(ALL_EQUAL), lineEvaluationService.evaluateLine(new Line(uuid(), 2, 2, 2)));
    }

    @Test
    public void testEvaluateLineExclusive1() {
        // Assert.assertEquals(Integer.valueOf(1), evaluationService.evaluateLine(new LineDto(0, 1, 1))); Ambiguous
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 1, 2)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 2, 1)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 0, 2, 2)));

        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 0, 0)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 0, 2)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 2, 0)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 1, 2, 2)));

        // Assert.assertEquals(Integer.valueOf(1), evaluationService.evaluateLine(new LineDto(2, 0, 0))); Ambiguous
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 2, 0, 1)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 2, 1, 0)));
        Assert.assertEquals(Integer.valueOf(FIRST_IS_DIFFERENT), lineEvaluationService.evaluateLine(new Line(uuid(), 2, 1, 1)));
    }

    @Test
    public void testEvaluateLineRemaining0() {
        Assert.assertEquals(Integer.valueOf(OTHERWISE), lineEvaluationService.evaluateLine(new Line(uuid(), 2, 0, 2)));
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }
}
