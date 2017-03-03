package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

import java.util.stream.IntStream;

/**
 * @author Connor Goulding
 */
public class DefaultLineEvaluationService implements LineEvaluationService {
    @Override
    public Integer evaluateLine(final Line line) {
        assert line != null;

        if (streamOf(line).sum() == 2) {
            return SUM_OF_TWO;
        } else if (streamOf(line).distinct().count() == 1) {
            return ALL_EQUAL;
        } else if (line.getNumber1() != line.getNumber2() && line.getNumber1() != line.getNumber3()) {
            return FIRST_IS_DIFFERENT;
        } else {
            return OTHERWISE;
        }
    }

    private IntStream streamOf(final Line line) {
        return IntStream.of(line.getNumber1(), line.getNumber2(), line.getNumber3());
    }
}
