package com.ingemark.assignment.products.hnb.service.impl;

import com.ingemark.assignment.products.hnb.config.HnbProperties;
import com.ingemark.assignment.products.hnb.dto.CurrencyExchangeDto;
import com.ingemark.assignment.products.hnb.service.CurrencyExchangeCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CurrencyExchangeCalculationServiceImpl implements CurrencyExchangeCalculationService {

  private final HnbProperties hnbProperties;
  private final WebClient hnbClient;

  public Mono<CurrencyExchangeDto> getCurrencyExchangeRate() {
    return Mono.just(CurrencyExchangeDto.builder().broj_tecajnice("28").datum_primjene("2023-02-08").drzava("Danska").drzava_iso("DNK")
                                        .sifra_valute("208").valuta("DKK").kupovni_tecaj("7,4527").srednji_tecaj("7,4415").prodajni_tecaj("7,4303")
                                        .build());

    // TODO: fix this
    //    return this.hnbClient.get().uri(uriBuilder -> uriBuilder.queryParam(hnbProperties.getCurrency(), hnbProperties.getValue()).build())
    //                         .retrieve().bodyToMono(CurrencyExchangeDto.class);
  }

}
