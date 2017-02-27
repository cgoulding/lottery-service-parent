package com.goulding.connor.stack.service.dao;

import com.goulding.connor.stack.service.domain.Line;

import java.util.List;

/**
 * Created by connor.
 */
public interface LineDao {
    void addLine(Line line);

    List<Line> readLines();
}
