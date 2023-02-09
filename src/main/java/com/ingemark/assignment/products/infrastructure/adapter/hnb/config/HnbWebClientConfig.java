package com.ingemark.assignment.products.infrastructure.adapter.hnb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class HnbWebClientConfig {

  private final HnbProperties properties;

  @Bean
  public WebClient hnbWebClient() {
    return WebClient.create(properties.getServerAddress());
  }

}
