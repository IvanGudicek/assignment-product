package com.ingemark.assignment.products.product.error;

import com.ingemark.assignment.products.error.AbstractControllerAdvice;
import com.ingemark.assignment.products.error.model.ErrorDto;
import com.ingemark.assignment.products.product.error.exception.ProductNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ProductControllerAdvice extends AbstractControllerAdvice {

  @ExceptionHandler(ProductNotFoundException.class)
  public Mono<ResponseEntity<ErrorDto>> productNotFoundExceptionHandler(ProductNotFoundException e) {
    return getErrorFromException(e, null);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public Mono<ResponseEntity<ErrorDto>> productWithCodeAlreadyExistExceptionHandler(DataIntegrityViolationException e) {
    return getErrorFromException(e, "Product with that code already exist!");
  }

}
