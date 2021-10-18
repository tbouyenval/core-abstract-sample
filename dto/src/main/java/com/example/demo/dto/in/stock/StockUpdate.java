package com.example.demo.dto.in.stock;

import javax.validation.Valid;
import java.util.List;

public record StockUpdate(@Valid List<ShoeUpdate> shoes) {
}
