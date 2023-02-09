package com.ingemark.assignment.products.infrastructure.adapter.hnb.impl;

import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculation;
import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeDto;
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

  private final WebClient hnbClient;

  @Override
  public Mono<BigDecimal> getCurrencyPrice(CurrencyExchangeCalculation currencyExchangeCalculation) {
    return convertToEuro(currencyExchangeCalculation.getCurrencyPrice());
  }

  private Mono<BigDecimal> convertToEuro(BigDecimal priceHrk) {
    return getCurrencyExchangeRate()
      .map(currencyExchangeCalculation ->
             BigDecimalUtil.roundBigDecimal(priceHrk).divide(BigDecimalUtil.toBigDecimal(currencyExchangeCalculation.getProdajni_tecaj()),
                                                             RoundingMode.HALF_EVEN));
  }

  private Mono<CurrencyExchangeDto> getCurrencyExchangeRate() {
    //    return Mono.just(CurrencyExchangeDto.builder().broj_tecajnice("28").datum_primjene("2023-02-08").drzava("Danska").drzava_iso("DNK")
    //                                        .sifra_valute("208").valuta("DKK").kupovni_tecaj("7,4527").srednji_tecaj("7,4415").prodajni_tecaj("7,4303")
    //                                        .build());

    return hnbClient.get()
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<CurrencyExchangeDto>>() {
                    })
                    .map(currencyList -> currencyList.get(0))
                    .onErrorResume(error -> Mono.error(new CurrencyExchangeConversionException(error.getMessage())));
  }

}
