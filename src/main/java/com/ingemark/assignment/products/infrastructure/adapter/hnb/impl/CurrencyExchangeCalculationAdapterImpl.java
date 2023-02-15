package com.ingemark.assignment.products.infrastructure.adapter.hnb.impl;

import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculation;
import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeDto;
import com.ingemark.assignment.products.infrastructure.adapter.hnb.config.HnbProperties;
import com.ingemark.assignment.products.rest.error.handling.CurrencyExchangeConversionException;
import com.ingemark.assignment.products.util.BigDecimalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CurrencyExchangeCalculationAdapterImpl implements CurrencyExchangeCalculationAdapter {

  private final HnbProperties hnbProperties;
  private final WebClient hnbClient;

  @Override
  public Mono<BigDecimal> getCurrencyPrice(CurrencyExchangeCalculation currencyExchangeCalculation) {
    return convertToEuro(currencyExchangeCalculation.getCurrencyPrice());
  }

  private Mono<BigDecimal> convertToEuro(BigDecimal priceHrk) {
    return getCurrencyExchangeRate()
      .map(currencyExchangeCalculation ->
             BigDecimalUtil.roundBigDecimal(priceHrk)
                           .divide(BigDecimalUtil.toBigDecimal(currencyExchangeCalculation.getProdajni_tecaj()), RoundingMode.HALF_EVEN));
  }

  private Mono<CurrencyExchangeDto> getCurrencyExchangeRate() {
    return hnbClient.get()
                    .uri(uriBuilder -> uriBuilder.path(hnbProperties.getCurrencyPath())
                                                 .queryParam(hnbProperties.getCurrencyName(), hnbProperties.getCurrencyValue())
                                                 .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<CurrencyExchangeDto>>() {
                    })
                    .map(currencyList -> currencyList.get(0))
                    .onErrorMap(error -> new CurrencyExchangeConversionException(error.getMessage()));
  }

}
