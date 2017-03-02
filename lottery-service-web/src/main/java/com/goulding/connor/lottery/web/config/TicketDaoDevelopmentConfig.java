/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.dao.FileBasedTicketDao;
import com.goulding.connor.lottery.service.dao.TicketDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */
@Profile("development")
public class TicketDaoDevelopmentConfig
{
    @Bean
    public TicketDao ticketDao() throws IOException {
        return new FileBasedTicketDao(createTemporaryDirectory());
    }

    private File createTemporaryDirectory() throws IOException
    {
        File file = File.createTempFile("TicketDaoTestConfig-", null);
        file.delete();
        file.mkdirs();
        file.deleteOnExit();
        return file;
    }
}
