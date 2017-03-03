package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

/**
 * @author Connor Goulding
 */
public interface LineEvaluationService {

    /**
     * All 3 numbers sum to 2
     */
    Integer SUM_OF_TWO = 10;

    /**
     * All 3 numbers are equal
     */
    Integer ALL_EQUAL = 5;

    /**
     * The first number is different
     */
    Integer FIRST_IS_DIFFERENT = 1;

    /**
     * All other cases
     */
    Integer OTHERWISE = 0;

    /**
     * Evaluate the line
     *
     * @param line
     * @return
     */
    Integer evaluateLine(Line line);
}
