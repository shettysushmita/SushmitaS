package com.jpmc.notificationservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.notificationservice.model.Sale;
import com.jpmc.notificationservice.service.ProcessingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private ProcessingService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void when_add_with_success() throws Exception {
        Sale sale = createSampleMessage();
        String request = mapper.writeValueAsString(sale);
        Mockito.doNothing().when(service).add(any(Sale.class));
        this.mockmvc.perform(post("/sale")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    private Sale createSampleMessage() {
        return new Sale("Sale","productType1",10.00);
    }
}
