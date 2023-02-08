package com.ingemark.assignment.products.hnb.error;

import com.ingemark.assignment.products.error.AbstractControllerAdvice;
import com.ingemark.assignment.products.error.model.ErrorDto;
import com.ingemark.assignment.products.hnb.error.exception.CurrencyExchangeConversionException;
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
