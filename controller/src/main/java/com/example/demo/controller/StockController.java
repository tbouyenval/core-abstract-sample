package com.example.demo.controller;

import com.example.demo.core.service.ShoeStockCore;
import com.example.demo.dto.in.stock.StockUpdate;
import com.example.demo.dto.out.stock.Stock;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/stock")
public class StockController {

    final ShoeStockCore shoeStockCore;

    @Autowired
    public StockController(ShoeStockCore shoeStockCore) {
        this.shoeStockCore = shoeStockCore;
    }

    @ApiOperation(value = "Get the stock and details", response = String.class)
    @GetMapping
    public ResponseEntity<List<Stock>> getStock(){

        return ResponseEntity.ok(shoeStockCore.getAllStock());
    }

    @ApiOperation(value = "Put one or more shoe quantity in stock", response = String.class)
    @PatchMapping
    public ResponseEntity patchStock(@Valid @RequestBody StockUpdate stockUpdate){

        shoeStockCore.updateStock(stockUpdate.shoes());
        return ResponseEntity.noContent().build();
    }
}
