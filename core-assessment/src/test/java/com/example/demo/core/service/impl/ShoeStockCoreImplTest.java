package com.example.demo.core.service.impl;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.stock.ShoeUpdate;
import com.example.demo.dto.out.stock.ShoeWithStock;
import com.example.demo.dto.out.stock.Stock;
import com.example.demo.dto.out.stock.StockState;
import com.example.demo.entities.ShoeEntity;
import com.example.demo.entities.StockEntity;
import com.example.demo.repositories.ShoeRepository;
import com.example.demo.repositories.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShoeStockCoreImplTest {

    @InjectMocks
    ShoeStockCoreImpl shoeStockCore;

    @Mock
    StockRepository stockRepository;

    @Mock
    ShoeRepository shoeRepository;

    @Test
    void testGetAllStock(){
        var shoeEntity = new ShoeEntity();
        shoeEntity.setId(Long.valueOf(42));
        shoeEntity.setName("test 42");

        var stock = new StockEntity();
        stock.setStockList(new HashSet<>());
        stock.getStockList().add(shoeEntity);
        stock.setId(Long.valueOf(43));

        Mockito.when(stockRepository.findAll()).thenReturn(List.of(stock));

        var result = shoeStockCore.getAllStock();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("test 42", result.get(0).shoes().iterator().next().name());
    }

    @Test
    void testupdateStockWithNewShoeModel(){
        var shoeEntity = new ShoeEntity();
        shoeEntity.setId(42L);
        shoeEntity.setName("test 42");
        shoeEntity.setColor("BLACK");
        shoeEntity.setSize(BigInteger.TWO);
        shoeEntity.setQuantity(3);
        var stockEntity = new StockEntity();
        stockEntity.setStockList(new HashSet<>());
        stockEntity.getStockList().add(shoeEntity);
        stockEntity.setId(43L);
        Mockito.when(stockRepository.findAll()).thenReturn(List.of(stockEntity));

        var shoeEntityUpdate = new ShoeEntity();
        shoeEntityUpdate.setId(33L);
        shoeEntityUpdate.setName("new Shoe");
        shoeEntityUpdate.setColor("BLACK");
        shoeEntityUpdate.setSize(BigInteger.ONE);
        shoeEntityUpdate.setQuantity(2);
        Mockito.when(shoeRepository.save(ArgumentMatchers.any())).thenReturn(shoeEntityUpdate);

        var shoeUpdate = new ShoeUpdate("new Shoe", BigInteger.ONE, ShoeFilter.Color.BLACK, 2);
        shoeStockCore.updateStock(List.of(shoeUpdate));

        verify(stockRepository, times(1)).save(ArgumentMatchers.any());
        Assertions.assertEquals(2, stockEntity.getStockList().size());
        Assertions.assertEquals(5, stockEntity.getStockTotal());
    }

    @Test
    void testupdateStockWithExistingShoeModel(){
        var shoeEntity = new ShoeEntity();
        shoeEntity.setId(42L);
        shoeEntity.setName("test 42");
        shoeEntity.setColor("BLACK");
        shoeEntity.setSize(BigInteger.TWO);
        shoeEntity.setQuantity(3);
        var stockEntity = new StockEntity();
        stockEntity.setStockList(new HashSet<>());
        stockEntity.getStockList().add(shoeEntity);
        stockEntity.setId(43L);
        Mockito.when(stockRepository.findAll()).thenReturn(List.of(stockEntity));

        var shoeUpdate = new ShoeUpdate("test 42", BigInteger.TWO, ShoeFilter.Color.BLACK, 1);
        shoeStockCore.updateStock(List.of(shoeUpdate));

        verify(stockRepository, times(1)).save(ArgumentMatchers.any());
        Assertions.assertEquals(1, stockEntity.getStockList().size());
        Assertions.assertEquals(1, stockEntity.getStockTotal());
    }

}
