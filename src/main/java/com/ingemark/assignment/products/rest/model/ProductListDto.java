package com.ingemark.assignment.products.rest.model;

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
public class ProductListDto extends AbstractApiServiceDto {

  private String name;
  private String code;
  private BigDecimal priceEur;
  private BigDecimal priceHrk;
  private Boolean available;

}
