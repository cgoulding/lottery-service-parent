package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.*;
import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.repository.EntityToModelTransformer;
import com.goulding.connor.lottery.service.repository.ModelToEntityTransformer;
import com.goulding.connor.lottery.service.repository.PersistingTicketRepository;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Connor Goulding
 */
@Configuration
@Import({TicketDaoDevelopmentConfig.class, TicketDaoProductionConfig.class})
public class LotteryServiceConfig {
    @Autowired
    private TicketDao ticketDao;

    @Bean
    public LotteryService lotteryService() {
        return new DefaultLotteryService(ticketRepository(), lineGenerationService(), lineEvaluationService());
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new PersistingTicketRepository(ticketDao, dtoToDomainTransformer(), domainToDtoTransformer());
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
