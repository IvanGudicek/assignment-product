package com.ingemark.assignment.products.hnb.service;

import com.ingemark.assignment.products.hnb.dto.CurrencyExchangeDto;
import reactor.core.publisher.Mono;

public interface CurrencyExchangeCalculationService {

  Mono<CurrencyExchangeDto> getCurrencyExchangeRate();

}
