package com.example.demo.dto.out.stock;

import com.example.demo.dto.in.ShoeFilter;

import java.math.BigInteger;

public record ShoeWithStock(String name, BigInteger size,
                            ShoeFilter.Color color, Integer quantity) {

}
