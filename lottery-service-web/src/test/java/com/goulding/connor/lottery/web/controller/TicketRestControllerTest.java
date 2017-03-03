package com.goulding.connor.lottery.web.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goulding.connor.lottery.service.LineEvaluationService;
import com.goulding.connor.lottery.service.model.Line;
import com.goulding.connor.lottery.service.model.Ticket;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import com.goulding.connor.lottery.web.LotteryServiceApplication;
import com.goulding.connor.lottery.web.resource.TicketResource;
import com.goulding.connor.lottery.web.resource.TicketStatusResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Connor Goulding
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LotteryServiceApplication.class)
public class TicketRestControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Ticket initialUncheckedTicket;
    private Ticket initialCheckedTicket;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.initialUncheckedTicket = this.ticketRepository.addTicket(Arrays.asList(
                new Line(UUID.randomUUID().toString(), 0, 1, 2)));

        Ticket added = this.ticketRepository.addTicket(Arrays.asList(
                new Line(UUID.randomUUID().toString(), 0, 1, 2)));
        Ticket checked = new Ticket(added.getTicketUuid(), added.getLines(), Calendar.getInstance().getTime());
        this.initialCheckedTicket = this.ticketRepository.updateTicket(checked);
    }

    @Test
    public void testTicketsGetOk() throws Exception {
        MvcResult result = mockMvc.perform(get("/tickets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        TicketResource[] tickets = fromJson(TicketResource[].class, result.getResponse().getContentAsString());
        TicketResource ticket = tickets[0];
        Assert.assertEquals(1, ticket.getLines().size());

        Line line = ticket.getLines().get(0);
        Assert.assertEquals(Integer.valueOf(0), line.getNumber1());
        Assert.assertEquals(Integer.valueOf(1), line.getNumber2());
        Assert.assertEquals(Integer.valueOf(2), line.getNumber3());
    }

    @Test
    public void testTicketsPutNotAllowed() throws Exception {
        mockMvc.perform(put("/tickets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testTicketsDeleteNotAllowed() throws Exception {
        mockMvc.perform(delete("/tickets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testTicketCreatePostCreated() throws Exception {
        MvcResult result = mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NumberOfLines(10))))
                .andExpect(status().isCreated())
                .andReturn();

        TicketResource created = fromJson(TicketResource.class, result.getResponse().getContentAsString());
        Assert.assertEquals(10, created.getLines().size());
    }

    @Test
    public void testTicketFindGetSuccess() throws Exception {
        MvcResult result = mockMvc.perform(get("/tickets/" + initialUncheckedTicket.getTicketUuid())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        TicketResource created = fromJson(TicketResource.class, result.getResponse().getContentAsString());
        Assert.assertEquals(initialUncheckedTicket.getTicketUuid(), created.getTicketUuid());
    }

    @Test
    public void testTicketFindGetNoContent() throws Exception {
        mockMvc.perform(get("/tickets/badTicketUuid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testTicketCreatePostBadRequest() throws Exception {
        mockMvc.perform(post("/tickets", "Bad Post Content")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTicketAmmendPutSuccess() throws Exception {
        MvcResult result = mockMvc.perform(put("/tickets/" + initialUncheckedTicket.getTicketUuid() + "/ammend")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NumberOfLines(3))))
                .andExpect(status().isAccepted())
                .andReturn();

        TicketResource ammended = fromJson(TicketResource.class, result.getResponse().getContentAsString());
        Assert.assertEquals(initialUncheckedTicket.getTicketUuid(), ammended.getTicketUuid());
        Assert.assertEquals(4, ammended.getLines().size()); //initial ticket size + the new 3 lines
    }

    @Test
    public void testTicketAmmendPutBadRequest() throws Exception {
        mockMvc.perform(put("/tickets/" + initialUncheckedTicket.getTicketUuid() + "/ammend")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Bad Body Content"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTicketAmmendPutAlreadyCheckedNotAcceptable() throws Exception {
        mockMvc.perform(put("/tickets/" + initialCheckedTicket.getTicketUuid() + "/ammend")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NumberOfLines(10))))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testTicketCheckPutSuccess() throws Exception {
        MvcResult result = mockMvc.perform(put("/tickets/" + initialUncheckedTicket.getTicketUuid() + "/check")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        TicketStatusResource checked = fromJson(TicketStatusResource.class, result.getResponse().getContentAsString());
        Assert.assertEquals(initialUncheckedTicket.getTicketUuid(), checked.getTicketUuid());
        Assert.assertEquals(1, checked.getLineResults().size());
        Assert.assertEquals(Integer.valueOf(LineEvaluationService.FIRST_IS_DIFFERENT),
                checked.getLineResults().get(0).getResult()); //0, 1, 2 = 1
    }

    @Test
    public void testTicketCheckPutAlreadyCheckedAccepted() throws Exception {
        mockMvc.perform(put("/tickets/" + initialCheckedTicket.getTicketUuid() + "/check")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    private <R> R fromJson(Class<R> resourceClass, String response) throws IOException {
        return objectMapper.readValue(response, resourceClass);
    }

    private String toJson(Object body) throws IOException {
        return objectMapper.writeValueAsString(body);
    }
}
