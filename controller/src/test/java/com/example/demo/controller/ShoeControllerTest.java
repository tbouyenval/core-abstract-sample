package com.example.demo.controller;

import com.example.demo.core.ShoeCoreLegacy;
import com.example.demo.core.ShoeCoreNew;
import com.example.demo.facade.ShoeFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShoeController.class)
class ShoeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShoeFacade shoeFacade;

    @Test
    void testSearchV1() throws Exception {
        Mockito.when(shoeFacade.get(1)).thenReturn(new ShoeCoreLegacy());

        mockMvc.perform(get("/shoes/search").header("version", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shoes", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.shoes[0].name", Matchers.is("Legacy shoe")));
    }

    @Test
    void testSearchV2() throws Exception {
        Mockito.when(shoeFacade.get(2)).thenReturn(new ShoeCoreNew());

        mockMvc.perform(get("/shoes/search").header("version", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shoes", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.shoes[0].name", Matchers.is("New shoe")));
    }
}
