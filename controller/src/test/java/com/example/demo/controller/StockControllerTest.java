package com.example.demo.controller;

import com.example.demo.core.ShoeCoreLegacy;
import com.example.demo.core.service.ShoeStockCore;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.stock.ShoeUpdate;
import com.example.demo.dto.in.stock.StockUpdate;
import com.example.demo.dto.out.stock.ShoeWithStock;
import com.example.demo.dto.out.stock.Stock;
import com.example.demo.dto.out.stock.StockState;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShoeStockCore shoeStockCore;

    @Test
    void testGetStock() throws Exception {
        var shoeWithStock = new ShoeWithStock("test", BigInteger.TWO, ShoeFilter.Color.BLACK, 1);
        var stock = new Stock(StockState.SOME, Set.of(shoeWithStock));
        Mockito.when(shoeStockCore.getAllStock()).thenReturn(List.of(stock));

        mockMvc.perform(get("/stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stockState", Matchers.is("SOME")))
                .andExpect(jsonPath("$[0].shoes[0].name", Matchers.is("test")));
    }

    @Test
    void testPatchStock() throws Exception {
        var jsonString = """
                {
                    "shoes": [
                        {
                            "name": "coin",
                            "size": "30",
                            "color": "BLACK",
                            "quantity": "3"
                        }
                    ]
                }
                """;

        Mockito.doNothing().when(shoeStockCore).updateStock(ArgumentMatchers.any());

        mockMvc.perform(patch("/stock").content(jsonString).header("Content-Type", "application/json"))
                .andExpect(status().isNoContent());
    }
}
