package com.goulding.connor.stack.service.repository;

import com.goulding.connor.stack.service.model.LineDto;

import java.util.Collection;

/**
 * Created by connor on 14/02/17.
 */
public interface LineRepository {

    void createLine(LineDto line);

    Collection<LineDto> readLines();

}
