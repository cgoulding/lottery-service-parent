/**
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 */

package com.goulding.connor.lottery.web.controller;

import com.goulding.connor.lottery.web.LotteryServiceApplication;
import com.goulding.connor.lottery.service.model.LineDto;
import com.goulding.connor.lottery.service.repository.TicketRepository;
import com.goulding.connor.lottery.web.config.LotteryServiceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * <p>
 * &copy; 2017 VCE Company, LLC. All rights reserved.
 * VCE Confidential/Proprietary Information
 * </p>
 *
 * @since SINCE-TBD
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LotteryServiceApplication.class)
@WebAppConfiguration
public class TicketRestControllerTest
{

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private TicketRepository ticketRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LotteryServiceConfig.class);
        this.ticketRepository = context.getBean(TicketRepository.class);

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.ticketRepository.addTicket(Arrays.asList(new LineDto(1, 1, 1)));
    }

    @Test
    public void readLines() throws Exception {
        mockMvc.perform(get("/line")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

//    @Test
//    public void readSingleBookmark() throws Exception {
//        mockMvc.perform(get("/" + userName + "/bookmarks/"
//                + this.bookmarkList.get(0).getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
//                .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
//                .andExpect(jsonPath("$.description", is("A description")));
//    }
//
//    @Test
//    public void readBookmarks() throws Exception {
//        mockMvc.perform(get("/" + userName + "/bookmarks"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
//                .andExpect(jsonPath("$[0].uri", is("http://bookmark.com/1/" + userName)))
//                .andExpect(jsonPath("$[0].description", is("A description")))
//                .andExpect(jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
//                .andExpect(jsonPath("$[1].uri", is("http://bookmark.com/2/" + userName)))
//                .andExpect(jsonPath("$[1].description", is("A description")));
//    }
//
//    @Test
//    public void createBookmark() throws Exception {
//        String bookmarkJson = json(new Bookmark(
//                this.account, "http://spring.io", "a bookmark to the best resource for Spring news and information"));
//
//        this.mockMvc.perform(post("/" + userName + "/bookmarks")
//                .contentType(contentType)
//                .content(bookmarkJson))
//                .andExpect(status().isCreated());
//    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}