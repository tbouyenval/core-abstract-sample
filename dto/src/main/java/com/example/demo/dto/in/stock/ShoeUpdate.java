package com.example.demo.dto.in.stock;

import com.example.demo.dto.in.ShoeFilter;

import javax.validation.constraints.*;
import java.math.BigInteger;

public record ShoeUpdate(@NotEmpty @NotBlank String name,
                         @NotNull BigInteger size,
                         @NotNull ShoeFilter.Color color,
                         @NotNull @Max(30) @Min(0) Integer quantity) {
}
