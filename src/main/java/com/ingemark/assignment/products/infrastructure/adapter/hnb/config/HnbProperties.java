package com.ingemark.assignment.products.infrastructure.adapter.hnb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "hnb.services.config")
@Configuration
public class HnbProperties {

  private String serverAddress;
  private String currencyPath;
  private String currencyName;
  private String currencyValue;

}
