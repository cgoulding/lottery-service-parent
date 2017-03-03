package com.goulding.connor.lottery.service;

import com.goulding.connor.lottery.service.model.Line;

import java.util.Random;

/**
 * @author Connor Goulding
 */
public class RandomLineGenerationService implements LineGenerationService {

    @Override
    public Line generateLine() {
        final Random random = new Random();
        return new Line(random.nextInt(3), random.nextInt(3), random.nextInt(3));
    }
}
