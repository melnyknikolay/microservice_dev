package it.discovery.monolith.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.discovery.monolith.MonolithApplication;

@SpringJUnitWebConfig(MonolithApplication.class)
@AutoConfigureMockMvc
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void updateBook_validBook_bookSaved() throws Exception {
    }

}
