package com.ingemark.assignment.products.rest.model;

import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends AbstractApiServiceDto implements CurrencyExchangeCalculation {

  @NotNull
  private String name;

  private String description;

  @NotBlank
  @Size(min = 10, max = 10)
  private String code;

  @NotNull
  @DecimalMin(value = "0.0")
  @Digits(integer = 10, fraction = 4)
  private BigDecimal priceHrk;

  @Digits(integer = 10, fraction = 4)
  private BigDecimal priceEur;

  private Boolean available;

  @Override
  public BigDecimal getCurrencyPrice() {
    return priceHrk;
  }

}
