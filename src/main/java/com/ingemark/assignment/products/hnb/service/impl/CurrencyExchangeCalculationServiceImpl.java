package com.ingemark.assignment.products.hnb.service.impl;

import com.ingemark.assignment.products.hnb.dto.CurrencyExchangeDto;
import com.ingemark.assignment.products.hnb.service.CurrencyExchangeCalculationService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CurrencyExchangeCalculationServiceImpl implements CurrencyExchangeCalculationService {

  public CurrencyExchangeDto getCurrencyExchangeRate() {
    return CurrencyExchangeDto.builder().broj_tecajnice("28").datum_primjene("2023-02-08").drzava("Danska").drzava_iso("DNK")
                              .sifra_valute("208").valuta("DKK").kupovni_tecaj("7,4527").srednji_tecaj("7,4415").prodajni_tecaj("7,4303").build();
  }

}
