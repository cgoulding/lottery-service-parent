package com.goulding.connor.lottery.service.dao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.goulding.connor.lottery.service.LottoServiceException;
import com.goulding.connor.lottery.service.entity.Line;
import com.goulding.connor.lottery.service.entity.Ticket;
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
 * Created by root on 20/02/17.
 */
public class FileBasedTicketDao implements TicketDao
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedTicketDao.class);

    private final File         directory;
    private final ObjectMapper objectMapper;

    public FileBasedTicketDao(final String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }

        this.directory = file;
        this.objectMapper = createObjectMapper();
    }

    public FileBasedTicketDao(final File directory) {
        this.directory = directory;
        this.objectMapper = createObjectMapper();
    }

    @Override
    public List<Ticket> readTickets()
    {
        try
        {
            return Files.list(Paths.get(directory.toURI())).map(this::transform).collect(Collectors.toList());
        }
        catch (IOException exception)
        {
            LOGGER.error("Unable to read ticket from file", exception);
            throw new LottoServiceException("Unable to write to file", exception);
        }
    }

    private Ticket transform(Path path) {
        try
        {
            return objectMapper.readValue(path.toFile(), Ticket.class);
        }
        catch (IOException exception)
        {
            LOGGER.error("Unable to read ticket from file", exception);
            throw new LottoServiceException("Unable to write to file", exception);
        }
    }

    @Override
    public Ticket createTicket(List<Line> lines)
    {
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), lines, null);
        try (FileOutputStream outputStream = new FileOutputStream(directory + File.separator + ticket.getTicketUuid())) {
            objectMapper.writeValue(outputStream, ticket);
        } catch (IOException exception) {
            LOGGER.error("Unable to write to file", exception);
            throw new LottoServiceException("Unable to write to file", exception);
        }

        return ticket;
    }

    @Override
    public Ticket readTicket(String ticketUuid)
    {
        File ticketSource = new File(directory + File.separator + ticketUuid);
        if (ticketSource.exists())
        {
            try (FileInputStream inputStream = new FileInputStream(directory + File.separator + ticketUuid)) {
                return objectMapper.readValue(inputStream, Ticket.class);
            } catch (IOException exception) {
                LOGGER.error("Unable to read from file", exception);
                throw new LottoServiceException("Unable to read from file", exception);
            }
        }
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket)
    {
        assert ticket != null;

        try (FileOutputStream outputStream = new FileOutputStream(directory + File.separator + ticket.getTicketUuid())) {
            objectMapper.writeValue(outputStream, ticket);
        } catch (IOException exception) {
            LOGGER.error("Unable to update to file", exception);
            throw new LottoServiceException("Unable to update to file", exception);
        }
        return ticket;
    }

    private ObjectMapper createObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm a z"));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
