package com.example.demo.core.service;

import com.example.demo.dto.in.stock.ShoeUpdate;
import com.example.demo.dto.out.stock.Stock;

import java.util.List;

public interface ShoeStockCore {

    /**
     * return the global state of stock
     */
    List<Stock> getAllStock();

    /**
     * createStock the new stock
     */
    void updateStock(List<ShoeUpdate> shoeUpdates);

}
