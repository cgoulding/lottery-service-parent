package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.DefaultLineEvaluationService;
import com.goulding.connor.lottery.service.DefaultLotteryService;
import com.goulding.connor.lottery.service.LineEvaluationService;
import com.goulding.connor.lottery.service.LineGenerationService;
import com.goulding.connor.lottery.service.LotteryService;
import com.goulding.connor.lottery.service.RandomLineGenerationService;
import com.goulding.connor.lottery.service.dao.FileBasedTicketDao;
import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.repository.EntityToModelTransformer;
import com.goulding.connor.lottery.service.repository.ModelToEntityTransformer;
import com.goulding.connor.lottery.service.repository.PersistedTicketRepository;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by connor.
 */
@Configuration
@EnableSwagger2
@PropertySources({
        @PropertySource("classpath:META-INF/spring/lottery-service-web/lottery-service.properties")
})
public class LotteryServiceConfig
{

    @Value("${lottery.service.ticket.dao.file.directory}")
    private String location;

    @Bean
    public LotteryService lotteryService() {
        return new DefaultLotteryService(ticketRepository(), lineGenerationService(), lineEvaluationService());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.goulding.connor.lottery"))
                .build()
                .apiInfo(apiInfo());
    }

    private TicketRepository ticketRepository() {
        return new PersistedTicketRepository(lineDao(), dtoToDomainTransformer(), domainToDtoTransformer());
    }

    private LineGenerationService lineGenerationService() {
        return new RandomLineGenerationService();
    }

    private LineEvaluationService lineEvaluationService() {
        return new DefaultLineEvaluationService();
    }

    private TicketDao lineDao() {
        return new FileBasedTicketDao(location);
    }

    private ModelToEntityTransformer dtoToDomainTransformer() {
        return new ModelToEntityTransformer();
    }

    private EntityToModelTransformer domainToDtoTransformer() {
        return new EntityToModelTransformer();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Lottery Service REST API",
                "Minimalistic Lottery Service for some fun.",
                "1.0",
                "Terms of service",
                new Contact("Connor Goulding", "www.goulding.com", "connor@goulding.com"),
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
