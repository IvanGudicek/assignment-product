package com.ingemark.assignment.products.infrastructure.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product {

  @Id
  @Column("id")
  private Long id;

  @Column("name")
  private String name;

  @Column("description")
  private String description;

  @Column("code")
  private String code;

  @Column("price_hrk")
  private BigDecimal priceHrk;

  @Column("price_eur")
  private BigDecimal priceEur;

  @Column("available")
  private Boolean available;

}