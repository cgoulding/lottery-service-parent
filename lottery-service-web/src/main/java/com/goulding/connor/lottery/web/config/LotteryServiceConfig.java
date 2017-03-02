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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by connor.
 */
@Configuration
@Import({TicketDaoDevelopmentConfig.class, TicketDaoProductionConfig.class})
public class LotteryServiceConfig
{
    @Autowired
    private TicketDao ticketDao;

    @Bean
    public LotteryService lotteryService() {
        return new DefaultLotteryService(ticketRepository(), lineGenerationService(), lineEvaluationService());
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new PersistedTicketRepository(ticketDao, dtoToDomainTransformer(), domainToDtoTransformer());
    }

    private LineGenerationService lineGenerationService() {
        return new RandomLineGenerationService();
    }

    private LineEvaluationService lineEvaluationService() {
        return new DefaultLineEvaluationService();
    }

    private ModelToEntityTransformer dtoToDomainTransformer() {
        return new ModelToEntityTransformer();
    }

    private EntityToModelTransformer domainToDtoTransformer() {
        return new EntityToModelTransformer();
    }
}
