package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

/**
 * @author Connor Goulding
 */
public interface LineGenerationService {

    /**
     * Generate a line for the ticket
     *
     * @return
     */
    Line generateLine();
}
