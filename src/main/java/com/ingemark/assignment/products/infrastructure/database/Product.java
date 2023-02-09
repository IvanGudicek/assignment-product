package com.ingemark.assignment.products.infrastructure.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends AbstractPersistable {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "code", unique = true)
  private String code;

  @Column(name = "price_hrk", precision = 14, scale = 4)
  private BigDecimal priceHrk;

  @Column(name = "price_eur", precision = 14, scale = 4)
  private BigDecimal priceEur;

  @Column(name = "available")
  private Boolean available;

}