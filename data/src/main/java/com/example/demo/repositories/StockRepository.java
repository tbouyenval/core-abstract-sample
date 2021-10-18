package com.example.demo.repositories;

import com.example.demo.entities.StockEntity;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<StockEntity, Long> {
}
