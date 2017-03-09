/*
 * &copy; 2016 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.dao.JpaTicketDao;
import com.goulding.connor.lottery.service.dao.TicketDao;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Persistence configuration.
 * <p>
 * &copy; 2016 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile({"development-jpa", "production-jpa"})
@EntityScan(basePackageClasses=TicketEntity.class)
public class JpaTicketDaoConfig
{
    @PersistenceContext
    private EntityManager em;

    @Bean
    public TicketDao ticketDao() {
        return new JpaTicketDao(em);
    }
}
