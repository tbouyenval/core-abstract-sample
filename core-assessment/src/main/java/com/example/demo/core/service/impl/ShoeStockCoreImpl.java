package com.example.demo.core.service.impl;

import com.example.demo.core.exception.StockFullException;
import com.example.demo.core.mapper.StockMapper;
import com.example.demo.core.service.ShoeStockCore;
import com.example.demo.dto.in.stock.ShoeUpdate;
import com.example.demo.dto.out.stock.Stock;
import com.example.demo.entities.ShoeEntity;
import com.example.demo.repositories.ShoeRepository;
import com.example.demo.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ShoeStockCoreImpl implements ShoeStockCore {

    private final StockRepository stockRepository;
    private final ShoeRepository shoeRepository;

    @Autowired
    public ShoeStockCoreImpl(StockRepository stockRepository, ShoeRepository shoeRepository) {
        this.stockRepository = stockRepository;
        this.shoeRepository = shoeRepository;
    }

    @Override
    public List<Stock> getAllStock() {
        final var stockEntities = stockRepository.findAll();
        return StreamSupport.stream(stockEntities.spliterator(), false)
                .map(stockEntity -> StockMapper.INSTANCE.stockEntityToStock(stockEntity))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateStock(List<ShoeUpdate> shoeUpdates) {
        var stockFromDatabase = stockRepository.findAll().iterator().next();

        var listOfShoesUpdate = shoeUpdates.stream().map(shoeUpdate -> {
            var showTemp = StockMapper.INSTANCE.shoeInputToShoeEntity(shoeUpdate);
            showTemp.setStock(stockFromDatabase);
            return showTemp;
        }).collect(Collectors.toList());

        //we search the stock who already exist
        var stockAlreadyExist = stockFromDatabase.getStockList().stream()
                .filter(shoeEntity -> listOfShoesUpdate.contains(shoeEntity))
                .map(shoeEntity -> {
                    shoeEntity.setQuantity(listOfShoesUpdate.get(listOfShoesUpdate.indexOf(shoeEntity)).getQuantity());
                    return shoeEntity;
                })
                .collect(Collectors.toList());

        //we add the rest
        stockFromDatabase.getStockList().addAll(listOfShoesUpdate.stream()
                .filter(shoeEntity -> !stockAlreadyExist.contains(shoeEntity))
                .map(shoeEntity -> shoeRepository.save(shoeEntity))
                .collect(Collectors.toList()));

        var quantityCount = stockFromDatabase.getStockList().stream().mapToInt(ShoeEntity::getQuantity).sum();

        if(quantityCount > 30){
            throw new StockFullException("Stock must be inferior than 30.");
        }

        stockFromDatabase.setStockTotal(quantityCount);

        stockRepository.save(stockFromDatabase);
    }
}
