package com.ingemark.assignment.products.infrastructure.adapter.hnb;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyExchangeCalculationAdapter {

  Mono<BigDecimal> getCurrencyPrice(CurrencyExchangeCalculation currencyExchangeCalculation);

}
