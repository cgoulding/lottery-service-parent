package com.goulding.connor.stack.web.rest;

import com.goulding.connor.stack.service.model.LineDto;
import com.goulding.connor.stack.service.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by connor on 14/02/17.
 */
@RestController
@RequestMapping(path = "/line")
public class LineRestController {

    @Autowired
    private LineRepository lineRepository;

    @RequestMapping(method = RequestMethod.GET)
    Collection<LineDto> readLines() {
        return lineRepository.readLines();
    }
}
