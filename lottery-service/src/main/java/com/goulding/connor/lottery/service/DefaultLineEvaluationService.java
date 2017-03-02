/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

import java.util.stream.IntStream;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
public class DefaultLineEvaluationService implements LineEvaluationService
{
    @Override
    public Integer evaluateLine(final Line line) {
        assert line != null;

        if (streamOf(line).sum() == 2){
            return 10;
        } else if (streamOf(line).distinct().count() == 1) {
            return 5;
        } else if (line.getNumber1() != line.getNumber2() && line.getNumber1() != line.getNumber3()) {
            return 1;
        } else {
            return 0;
        }
    }

    private IntStream streamOf(final Line line) {
        return IntStream.of(line.getNumber1(), line.getNumber2(), line.getNumber3());
    }
}
