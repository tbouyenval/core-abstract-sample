package com.example.demo.core;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.ShoeFilter.Color;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Shoes;
import java.math.BigInteger;
import java.util.List;

@Implementation(version = 1)
public class ShoeCoreLegacy extends AbstractShoeCore {

  @Override
  public Shoes search(final ShoeFilter filter) {
    var shoe = new Shoe("Legacy shoe", BigInteger.ONE, Color.BLUE);
    return new Shoes(List.of(shoe));
  }
}
