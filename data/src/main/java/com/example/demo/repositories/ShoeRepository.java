package com.example.demo.repositories;

import com.example.demo.entities.ShoeEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShoeRepository extends CrudRepository<ShoeEntity, Long> {
}
