package com.example.demo.dto.out.stock;

import java.util.Set;

public record Stock (StockState stockState, Set<ShoeWithStock> shoes ){

}
