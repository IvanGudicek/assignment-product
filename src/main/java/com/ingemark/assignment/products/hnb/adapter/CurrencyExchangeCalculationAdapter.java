package com.ingemark.assignment.products.hnb.adapter;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyExchangeCalculationAdapter {

  Mono<BigDecimal> getCurrencyPrice();

}
