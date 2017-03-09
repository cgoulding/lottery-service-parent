package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.dao.FileBasedTicketDao;
import com.goulding.connor.lottery.service.dao.TicketDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Connor Goulding
 */
@PropertySources({
        @PropertySource("classpath:META-INF/spring/lottery-service-web/lottery-service.properties")
})
@Profile({"production-filebased"})
public class FileBasedTicketDaoProductionConfig {
    @Value("${lottery.service.ticket.dao.file.directory}")
    private String location;

    @Bean
    public TicketDao ticketDao() {
        return new FileBasedTicketDao(location);
    }
}
