package com.ingemark.assignment.products.hnb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class HnbWebClientConfig {

  public static final String HNB_CLIENT = "hnbClient";
  private final HnbProperties properties;

  @Bean(HNB_CLIENT)
  public WebClient hnbWebClient() {
    return WebClient.builder()
                    .baseUrl(properties.getServerAddress())
                    .build();
  }

}
