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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by connor.
 */
@Api("Ticket Rest Controller")
@RestController
@RequestMapping(path = "/tickets")
public class TicketRestController
{
    @Autowired
    private LotteryService lotteryService;

    @ApiOperation("Find tickets")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketResource>> findAll()
    {
        List<TicketResource> ticketResources = lotteryService.readAllTickets().stream()
                .map(this::createTicketResource)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ticketResources, HttpStatus.OK);
    }

    @ApiOperation("Create ticket")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResource> create(@RequestBody String numberOfLines) {
        Ticket ticketDto = lotteryService.generateTicket(Integer.valueOf(numberOfLines));

        TicketResource resource = createTicketResource(ticketDto);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApiOperation("Find ticket")
    @RequestMapping(value = "/{ticketUuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResource> find(@PathVariable(value = "ticketUuid") String ticketUuid) {
        Ticket ticketDto = lotteryService.findTicket(ticketUuid);
        if (ticketDto == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TicketResource resource = createTicketResource(ticketDto);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    @ApiOperation("Ammend ticket")
    @RequestMapping(value = "/{ticketUuid}/ammend", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResource> ammend(
            @PathVariable(value = "ticketUuid") String ticketUuid,
            @RequestParam(value = "numberOfLines", defaultValue = "1") String numberOfLines) {
        Ticket ticketDto = lotteryService.ammendTicket(ticketUuid, Integer.valueOf(numberOfLines));
        if (ticketDto == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TicketResource resource = createTicketResource(ticketDto);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    @ApiOperation("Check ticket")
    @RequestMapping(value = "/{ticketUuid}/check", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketStatusResource> check(@PathVariable(value = "ticketUuid") String ticketUuid) {

        TicketResult resultDto = lotteryService.checkStatus(ticketUuid);
        if (resultDto == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TicketStatusResource resource = new TicketStatusResource(resultDto);
        resource.add(linkTo(methodOn(TicketRestController.class).find(ticketUuid)).withSelfRel());
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    private TicketResource createTicketResource(Ticket ticketDto)
    {
        TicketResource resource = new TicketResource(ticketDto);
        resource.add(linkTo(methodOn(TicketRestController.class).find(ticketDto.getTicketUuid())).withSelfRel());
        return resource;
    }
}
