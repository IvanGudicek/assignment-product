package com.ingemark.assignment.products.product.dto;

import com.ingemark.assignment.products.hnb.CurrencyExchangeCalculation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends AbstractApiServiceDto implements CurrencyExchangeCalculation {

  private String name;
  private String description;
  private String code;
  private BigDecimal priceHrk;
  private BigDecimal priceEur;
  private Boolean available;

  @Override
  public BigDecimal getCurrencyPrice() {
    return priceHrk;
  }

}
