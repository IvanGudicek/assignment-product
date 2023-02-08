package com.ingemark.assignment.products.hnb.adapter.impl;

import com.ingemark.assignment.products.hnb.CurrencyExchangeCalculation;
import com.ingemark.assignment.products.hnb.adapter.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.hnb.service.CurrencyExchangeCalculationService;
import com.ingemark.assignment.products.util.BigDecimalUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyExchangeCalculationAdapterImpl implements CurrencyExchangeCalculationAdapter {

  private CurrencyExchangeCalculation currencyExchangeCalculation;
  private CurrencyExchangeCalculationService currencyExchangeCalculationService;

  public CurrencyExchangeCalculationAdapterImpl(CurrencyExchangeCalculation currencyExchangeCalculation,
                                                CurrencyExchangeCalculationService currencyExchangeCalculationService) {
    this.currencyExchangeCalculation = currencyExchangeCalculation;
    this.currencyExchangeCalculationService = currencyExchangeCalculationService;
  }

  @Override
  public BigDecimal getCurrencyPrice() {
    return convertToEuro(currencyExchangeCalculation.getCurrencyPrice());
  }

  private BigDecimal convertToEuro(BigDecimal priceHrk) {
    String priceStringValue = currencyExchangeCalculationService.getCurrencyExchangeRate().getProdajni_tecaj();
    return BigDecimalUtil.roundBigDecimal(priceHrk).divide(BigDecimalUtil.toBigDecimal(priceStringValue), RoundingMode.HALF_EVEN);
  }

}
