package com.org.onlinebookstore.restcontroller;

import com.org.onlinebookstore.entity.Book;
import com.org.onlinebookstore.service.OnlineBookStoreService;
import com.org.onlinebookstore.service.OnlineBookStoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = OnlineBookStoreServiceImpl.class)
public class OnlineBookStoreAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OnlineBookStoreServiceImpl onlineBookStoreService;

    Book mockBookRecord = new Book(1, "Into the Universe", "Story about travel to the universe", "Ishaq Siddique", "Fiction", 10000.0);

    @Test
    public void retrieveDetailsForBook() throws Exception {

        Mockito.when(onlineBookStoreService.getBookDetailById(Mockito.anyInt())).thenReturn(mockBookRecord);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"isbn\":\"1\",\"name\":\"Into the Universe\",\"description\":\"Story about travel to the universe\",\"author\":\"Ishaq Siddique\",\"bookType\":\"Fiction\",\"price\":\"10000.0\"}";
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}