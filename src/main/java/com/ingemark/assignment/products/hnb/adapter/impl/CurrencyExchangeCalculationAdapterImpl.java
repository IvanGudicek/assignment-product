package com.ingemark.assignment.products.hnb.adapter.impl;

import com.ingemark.assignment.products.hnb.CurrencyExchangeCalculation;
import com.ingemark.assignment.products.hnb.adapter.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.hnb.service.CurrencyExchangeCalculationService;
import com.ingemark.assignment.products.util.BigDecimalUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class CurrencyExchangeCalculationAdapterImpl implements CurrencyExchangeCalculationAdapter {

  private final CurrencyExchangeCalculation currencyExchangeCalculation;
  private final CurrencyExchangeCalculationService currencyExchangeCalculationService;

  @Override
  public Mono<BigDecimal> getCurrencyPrice() {
    return convertToEuro(currencyExchangeCalculation.getCurrencyPrice());
  }

  private Mono<BigDecimal> convertToEuro(BigDecimal priceHrk) {
    return currencyExchangeCalculationService.getCurrencyExchangeRate().map(currencyExchangeCalculation ->
                                                                              BigDecimalUtil.roundBigDecimal(priceHrk)
                                                                                            .divide(BigDecimalUtil.toBigDecimal(currencyExchangeCalculation.getProdajni_tecaj()),
                                                                                                    RoundingMode.HALF_EVEN)
    );
  }

}
