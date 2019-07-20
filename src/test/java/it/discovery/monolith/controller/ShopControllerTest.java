package it.discovery.monolith.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.discovery.monolith.MonolithApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(MonolithApplication.class)
@AutoConfigureMockMvc
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void updateBook_validBook_bookSaved() throws Exception {
    }

    @Test
    void getLibrary_returnsLibraryName() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/shop/library"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().string("IT-Discovery library"));
    }

}
