/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class LineTest {
    @Test
    public void testNulls() {
        Line line = new Line(null, null, null, null);
        Assert.assertNull(line.getLineUuid());
        Assert.assertNull(line.getNumber1());
        Assert.assertNull(line.getNumber2());
        Assert.assertNull(line.getNumber3());
    }

    @Test
    public void testNotNulls() {
        Line line = new Line(uuid(), 0, 1, 2);
        Assert.assertEquals(Integer.valueOf(0), line.getNumber1());
        Assert.assertEquals(Integer.valueOf(1), line.getNumber2());
        Assert.assertEquals(Integer.valueOf(2), line.getNumber3());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(new Line(uuid(), 0, 1, 2).equals(new Line(uuid(), 0, 1, 2))); // Random equals check
        Assert.assertFalse(new Line(uuid(), 0, 0, 0).equals(new Line(uuid(), 1, 1, 1))); // Random not equals check
    }

    @Test
    public void testHashCode() {
        Assert.assertTrue(new Line(uuid(), 0, 1, 2).hashCode() == new Line(uuid(), 0, 1, 2).hashCode()); // Random hashcode check
        Assert.assertFalse(new Line(uuid(), 0, 0, 0).hashCode() == new Line(uuid(), 1, 1, 1).hashCode()); // Random hashcode check
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }
}
