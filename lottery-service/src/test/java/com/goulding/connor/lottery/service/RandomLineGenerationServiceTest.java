/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
@RunWith(Theories.class)
public class RandomLineGenerationServiceTest
{
    private static LineGenerationService LINE_GENERATION_SERVICE;
    private static Set<Integer> RANGE = new HashSet<>(Arrays.asList(0, 1, 2));

    @BeforeClass
    public static void setUp()
    {
        LINE_GENERATION_SERVICE = new RandomLineGenerationService();
    }

    @Theory
    public void testGenerateLine(Line line)
    {
        numbers(line).stream().map(n -> RANGE.contains(n)).forEach(Assert::assertTrue);
    }

    @DataPoints
    public static Line[] generateLines()
    {
        // Generate random test lines
        return new Line[]{LINE_GENERATION_SERVICE.generateLine(),
                LINE_GENERATION_SERVICE.generateLine(),
                LINE_GENERATION_SERVICE.generateLine(),
                LINE_GENERATION_SERVICE.generateLine(),
                LINE_GENERATION_SERVICE.generateLine()};
    }

    private Set<Integer> numbers(Line line)
    {
        return new HashSet<>(Arrays.asList(line.getNumber1(), line.getNumber2(), line.getNumber3()));
    }
}
