package com.goulding.connor.stack.service.repository;

import com.goulding.connor.stack.service.domain.Line;
import com.goulding.connor.stack.service.model.LineDto;

import java.util.UUID;

/**
 * Created by connor.
 */
public class DtoToDomainTransformer {

    public Line transform(LineDto dto) {
        return new Line(UUID.randomUUID().getMostSignificantBits(), dto.getNumber1(), dto.getNumber2(), dto.getNumber3());
    }
}
