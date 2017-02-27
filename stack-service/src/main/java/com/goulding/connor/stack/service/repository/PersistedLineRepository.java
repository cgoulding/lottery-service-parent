package com.goulding.connor.stack.service.repository;

import com.goulding.connor.stack.service.dao.LineDao;
import com.goulding.connor.stack.service.domain.Line;
import com.goulding.connor.stack.service.model.LineDto;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by connor on 14/02/17.
 */
public class PersistedLineRepository implements LineRepository {

    private final LineDao                lineDao;
    private final DtoToDomainTransformer dtoToDomainTransformer;
    private final DomainToDtoTransformer domainToDtoTransformer;

    public PersistedLineRepository(final LineDao lineDao, final DtoToDomainTransformer dtoToDomainTransformer,
                                   final DomainToDtoTransformer domainToDtoTransformer) {
        this.lineDao = lineDao;
        this.dtoToDomainTransformer = dtoToDomainTransformer;
        this.domainToDtoTransformer = domainToDtoTransformer;
    }

    @Override
    public void createLine(LineDto dto) {
        Line line = dtoToDomainTransformer.transform(dto);
        lineDao.addLine(line);
    }

    @Override
    public Collection<LineDto> readLines() {
        return lineDao.readLines().stream().map(domainToDtoTransformer::transform).collect(Collectors.toList());
    }
}
