package com.goulding.connor.lottery.service.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Connor Goulding
 */
public class LineResultTest {

    @Test
    public void testNulls() {
        LineResult lineResult = new LineResult(null, null);
        Assert.assertNull(lineResult.getLine());
        Assert.assertNull(lineResult.getResult());
    }

    @Test
    public void testNotNulls() {
        Line line = new Line(uuid(), 0, 1, 2);
        LineResult lineResult = new LineResult(line, 9);
        Assert.assertSame(line, lineResult.getLine());
        Assert.assertEquals(Integer.valueOf(9), lineResult.getResult());
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }
}
