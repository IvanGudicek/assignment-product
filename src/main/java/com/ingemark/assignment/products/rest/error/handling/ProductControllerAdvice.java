package com.ingemark.assignment.products.rest.error.handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ProductControllerAdvice extends AbstractControllerAdvice {

  @ExceptionHandler(ProductDuplicateCodeException.class)
  public Mono<ResponseEntity<ErrorDto>> productDuplicateCodeExceptionHandler(ProductDuplicateCodeException e) {
    return getErrorFromException(e);
  }

}
