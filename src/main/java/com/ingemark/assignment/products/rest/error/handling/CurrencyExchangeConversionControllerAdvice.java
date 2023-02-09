package com.ingemark.assignment.products.rest.error.handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CurrencyExchangeConversionControllerAdvice extends AbstractControllerAdvice {

  @ExceptionHandler(CurrencyExchangeConversionException.class)
  public Mono<ResponseEntity<ErrorDto>> currencyExchangeConversionExceptionHandler(CurrencyExchangeConversionException e) {
    return getErrorFromException(e, null);
  }

}
