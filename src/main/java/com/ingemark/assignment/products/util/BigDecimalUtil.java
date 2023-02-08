package com.ingemark.assignment.products.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BigDecimalUtil {

  public static BigDecimal toBigDecimal(String string) {
    String replacedString = string.replace(',', '.');
    return BigDecimal.valueOf(Double.parseDouble(replacedString)).setScale(4, RoundingMode.HALF_EVEN);
  }

  public static BigDecimal roundBigDecimal(BigDecimal bigDecimal) {
    return bigDecimal.setScale(4, RoundingMode.HALF_EVEN);
  }

}
