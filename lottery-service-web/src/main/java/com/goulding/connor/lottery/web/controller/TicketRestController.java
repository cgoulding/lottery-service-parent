package com.goulding.connor.lottery.web.controller;

import com.goulding.connor.lottery.service.LotteryService;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.model.TicketResult;
import com.goulding.connor.lottery.web.resource.TicketResource;
import com.goulding.connor.lottery.web.resource.TicketStatusResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Connor Goulding
 */
@Api("Ticket Rest Controller")
@RestController
@RequestMapping(path = "/tickets")
public class TicketRestController {
    @Autowired
    private LotteryService lotteryService;

    @ApiOperation(value = "Find tickets")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketResource>> findAll() {
        List<TicketResource> ticketResources = lotteryService.readAllTickets().stream()
                .map(this::createTicketResource)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ticketResources, HttpStatus.OK);
    }

    @ApiOperation(value = "Create ticket")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResource> create(@RequestBody NumberOfLines numberOfLines) {
        if (numberOfLines.getNumberOfLines() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Ticket ticketDto = lotteryService.generateTicket(numberOfLines.getNumberOfLines());

        TicketResource resource = createTicketResource(ticketDto);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @ApiOperation("Find ticket")
    @RequestMapping(value = "/{ticketUuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResource> find(@PathVariable(value = "ticketUuid") String ticketUuid) {
        Ticket ticket = lotteryService.findTicket(ticketUuid);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TicketResource resource = createTicketResource(ticket);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    @ApiOperation("Ammend ticket")
    @RequestMapping(value = "/{ticketUuid}/ammend", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResource> ammend(@PathVariable(value = "ticketUuid") String ticketUuid,
                                                 @RequestBody NumberOfLines numberOfLines) {
        if (numberOfLines.getNumberOfLines() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Ticket ticket = lotteryService.ammendTicket(ticketUuid, numberOfLines.getNumberOfLines());
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if (ticket.getCheckedTime() != null) {
            TicketResource resource = createTicketResource(ticket);
            return new ResponseEntity(resource, HttpStatus.NOT_ACCEPTABLE);
        } else {
            TicketResource resource = createTicketResource(ticket);
            return new ResponseEntity(resource, HttpStatus.ACCEPTED);
        }
    }

    @ApiOperation("Check ticket")
    @RequestMapping(value = "/{ticketUuid}/check", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketStatusResource> check(@PathVariable(value = "ticketUuid") String ticketUuid) {

        TicketResult result = lotteryService.checkStatus(ticketUuid);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TicketStatusResource resource = new TicketStatusResource(result);
        resource.add(linkTo(methodOn(TicketRestController.class).find(ticketUuid)).withSelfRel());
        return new ResponseEntity(resource, HttpStatus.ACCEPTED);
    }

    private TicketResource createTicketResource(Ticket ticketDto) {
        TicketResource resource = new TicketResource(ticketDto);
        resource.add(linkTo(methodOn(TicketRestController.class).find(ticketDto.getTicketUuid())).withSelfRel());
        resource.add(linkTo(methodOn(TicketRestController.class).check(ticketDto.getTicketUuid())).withRel("check"));
        if (ticketDto.getCheckedTime() == null) {
            resource.add(linkTo(methodOn(TicketRestController.class).ammend(ticketDto.getTicketUuid(),
                    new NumberOfLines(1))).withRel("ammend"));
        }
        return resource;
    }
}
