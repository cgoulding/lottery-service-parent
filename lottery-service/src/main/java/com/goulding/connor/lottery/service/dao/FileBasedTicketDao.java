package com.goulding.connor.lottery.service.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.goulding.connor.lottery.service.LotteryServiceException;
import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Connor Goulding
 */
public class FileBasedTicketDao implements TicketDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedTicketDao.class);

    private final File directory;
    private final ObjectMapper objectMapper;

    /**
     * Constructor with specified directory name
     *
     * @param directory - Name of the directory to store the files
     */
    public FileBasedTicketDao(final String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }

        this.directory = file;
        this.objectMapper = createObjectMapper();
    }

    /**
     * Constructor with specified directory file
     *
     * @param directory - The file reference to the directory to store the files
     */
    public FileBasedTicketDao(final File directory) {
        this.directory = directory;
        this.objectMapper = createObjectMapper();
    }

    /**
     * Read tickets from file directory
     *
     * @return
     * @throws LotteryServiceException
     */
    @Override
    public synchronized List<TicketEntity> readTickets() throws LotteryServiceException {
        try {
            return Files.list(Paths.get(directory.toURI())).map(this::transform).collect(Collectors.toList());
        } catch (IOException exception) {
            LOGGER.error("Unable to read ticket from file", exception);
            throw new LotteryServiceException("Unable to write to file", exception);
        }
    }

    /**
     * Create ticket with specified number of lines and save to file in directory
     *
     * @param lines
     * @return
     * @throws LotteryServiceException
     */
    @Override
    public synchronized TicketEntity createTicket(List<LineEntity> lines) throws LotteryServiceException {
        TicketEntity ticket = new TicketEntity(UUID.randomUUID().toString(), lines, null);
        try (FileOutputStream outputStream = new FileOutputStream(directory + File.separator + ticket.getTicketUuid())) {
            objectMapper.writeValue(outputStream, ticket);
        } catch (IOException exception) {
            LOGGER.error("Unable to write to file", exception);
            throw new LotteryServiceException("Unable to write to file", exception);
        }

        return ticket;
    }

    /**
     * Read ticket from directory with specified ticketUuid
     *
     * @param ticketUuid
     * @return
     * @throws LotteryServiceException
     */
    @Override
    public synchronized TicketEntity readTicket(String ticketUuid) throws LotteryServiceException {
        File ticketSource = new File(directory + File.separator + ticketUuid);
        if (ticketSource.exists()) {
            try (FileInputStream inputStream = new FileInputStream(directory + File.separator + ticketUuid)) {
                return objectMapper.readValue(inputStream, TicketEntity.class);
            } catch (IOException exception) {
                LOGGER.error("Unable to read from file", exception);
                throw new LotteryServiceException("Unable to read from file", exception);
            }
        }
        return null;
    }

    /**
     * Update specified ticket
     *
     * @param ticket
     * @return
     * @throws LotteryServiceException
     */
    @Override
    public synchronized TicketEntity updateTicket(TicketEntity ticket) throws LotteryServiceException {
        assert ticket != null;

        try (FileOutputStream outputStream = new FileOutputStream(directory + File.separator + ticket.getTicketUuid())) {
            objectMapper.writeValue(outputStream, ticket);
        } catch (IOException exception) {
            LOGGER.error("Unable to update to file", exception);
            throw new LotteryServiceException("Unable to update to file", exception);
        }
        return ticket;
    }

    private ObjectMapper createObjectMapper() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); //ISO Date Format
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    private TicketEntity transform(Path path) throws LotteryServiceException {
        try {
            return objectMapper.readValue(path.toFile(), TicketEntity.class);
        } catch (IOException exception) {
            LOGGER.error("Unable to read ticket from file", exception);
            throw new LotteryServiceException("Unable to write to file", exception);
        }
    }
}
