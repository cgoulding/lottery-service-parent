package com.goulding.connor.stack.service.repository;

import com.goulding.connor.stack.service.model.LineDto;

import java.util.Collection;

/**
 * Created by connor.
 */
public interface LineRepository {

    void createLine(LineDto line);

    Collection<LineDto> readLines();

}
