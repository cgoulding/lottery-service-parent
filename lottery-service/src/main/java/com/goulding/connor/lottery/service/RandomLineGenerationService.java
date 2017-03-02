/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

import java.util.Random;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class RandomLineGenerationService implements LineGenerationService
{
    @Override
    public Line generateLine()
    {
        final Random random = new Random();
        return new Line(random.nextInt(3), random.nextInt(3), random.nextInt(3));
    }
}
