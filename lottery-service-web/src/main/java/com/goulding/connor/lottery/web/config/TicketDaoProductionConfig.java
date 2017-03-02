/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.dao.FileBasedTicketDao;
import com.goulding.connor.lottery.service.dao.TicketDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
@PropertySources({
        @PropertySource("classpath:META-INF/spring/lottery-service-web/lottery-service.properties")
})
@Profile("production")
public class TicketDaoProductionConfig
{
    @Value("${lottery.service.ticket.dao.file.directory}")
    private String location;

    @Bean
    public TicketDao ticketDao() {
        return new FileBasedTicketDao(location);
    }
}
