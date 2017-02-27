package com.goulding.connor.stack.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goulding.connor.stack.service.dao.FileBasedLineDao;
import com.goulding.connor.stack.service.dao.LineDao;
import com.goulding.connor.stack.service.repository.DomainToDtoTransformer;
import com.goulding.connor.stack.service.repository.DtoToDomainTransformer;
import com.goulding.connor.stack.service.repository.LineRepository;
import com.goulding.connor.stack.service.repository.PersistedLineRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by connor.
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:META-INF/spring/lottery-service/lottery-service.properties")
})
public class StackConfig {

    @Value("${stack.linedao.file.location}")
    private String location;

    @Bean
    public LineRepository lineRepository() {
        return new PersistedLineRepository(lineDao(), dtoToDomainTransformer(), domainToDtoTransformer());
    }

    private LineDao lineDao() {
        return new FileBasedLineDao(location, new ObjectMapper());
    }

    private DtoToDomainTransformer dtoToDomainTransformer() {
        return new DtoToDomainTransformer();
    }

    private DomainToDtoTransformer domainToDtoTransformer() {
        return new DomainToDtoTransformer();
    }
}
