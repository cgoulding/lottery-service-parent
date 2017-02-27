package com.goulding.connor.stack.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goulding.connor.stack.service.repository.DomainToDtoTransformer;
import com.goulding.connor.stack.service.repository.DtoToDomainTransformer;
import com.goulding.connor.stack.service.repository.LineRepository;
import com.goulding.connor.stack.service.repository.PersistedLineRepository;
import com.goulding.connor.stack.service.dao.FileBasedLineDao;
import com.goulding.connor.stack.service.dao.LineDao;
import com.goulding.connor.stack.service.DefaultLotteryService;
import com.goulding.connor.stack.service.LineService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by connor on 14/02/17.
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:META-INF/spring/stack-service/stack-service.properties")
})
public class StackConfig {

    @Value("${stack.linedao.file.location}")
    private String location;

    @Bean
    public LineService lineService() {
        return new DefaultLotteryService(lineRepository());
    }

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
