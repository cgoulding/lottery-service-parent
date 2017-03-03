package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

import java.util.Random;
import java.util.UUID;

/**
 * @author Connor Goulding
 */
public class RandomLineGenerationService implements LineGenerationService {

    /**
     * Generate a line with random numbers between from 0 to 2
     * @return
     */
    @Override
    public Line generateLine() {
        final Random random = new Random();
        return new Line(UUID.randomUUID().toString(), random.nextInt(3), random.nextInt(3), random.nextInt(3));
    }
}
