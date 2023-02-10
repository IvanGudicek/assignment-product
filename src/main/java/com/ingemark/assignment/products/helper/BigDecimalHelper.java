package com.ingemark.assignment.products.helper;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public final class BigDecimalHelper {

  public BigDecimal toBigDecimal(String string) {
    String replacedString = string.replace(',', '.');
    return roundBigDecimal(BigDecimal.valueOf(Double.parseDouble(replacedString)));
  }

  public BigDecimal roundBigDecimal(BigDecimal bigDecimal) {
    return bigDecimal.setScale(4, RoundingMode.HALF_EVEN);
  }

}
