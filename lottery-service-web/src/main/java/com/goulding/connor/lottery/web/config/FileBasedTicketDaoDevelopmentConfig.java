package com.goulding.connor.lottery.web.config;

import com.goulding.connor.lottery.service.dao.FileBasedTicketDao;
import com.goulding.connor.lottery.service.dao.TicketDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;

/**
 * @author Connor Goulding
 */
@Profile({"development-filebased"})
public class FileBasedTicketDaoDevelopmentConfig {
    @Bean
    public TicketDao ticketDao() throws IOException {
        return new FileBasedTicketDao(createTemporaryDirectory());
    }

    private File createTemporaryDirectory() throws IOException {
        File file = File.createTempFile("FileBasedTicketDao-", null);
        file.delete();
        file.mkdirs();
        file.deleteOnExit();
        return file;
    }
}
