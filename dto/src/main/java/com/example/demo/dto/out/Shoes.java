package com.example.demo.dto.out;

import com.example.demo.dto.out.Shoes.ShoesBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = ShoesBuilder.class)
public record Shoes(List<Shoe> shoes) {

  @JsonPOJOBuilder(withPrefix = "")
  public static class ShoesBuilder {

  }


}
