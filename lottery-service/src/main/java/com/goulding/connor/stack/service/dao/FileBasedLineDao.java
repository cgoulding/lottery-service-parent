package com.goulding.connor.stack.service.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goulding.connor.stack.service.domain.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 20/02/17.
 */
public class FileBasedLineDao implements LineDao
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedLineDao.class);

    private final String location;
    private final ObjectMapper objectMapper;

    public FileBasedLineDao(final String location, final ObjectMapper objectMapper) {
        this.location = location;
        this.objectMapper = objectMapper;
    }

    @Override
    public void addLine(Line line) {
        List<Line> current = readFromFile();
        current.add(line);
        saveToFile(current);
    }

    @Override
    public List<Line> readLines() {
        return readFromFile();
    }

    private List<Line> readFromFile() {
        try (FileInputStream inputStream = new FileInputStream(location)) {
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(inputStream, Line[].class)));
        } catch (IOException exception) {
            LOGGER.error("Unable to read from file", exception);
        }
        return new ArrayList<>();
    }

    private void saveToFile(List<Line> lines) {
        try (FileOutputStream outputStream = new FileOutputStream(location)) {
            objectMapper.writeValue(outputStream, lines);
        } catch (IOException exception) {
            LOGGER.error("Unable to write to file", exception);
        }
    }
}
