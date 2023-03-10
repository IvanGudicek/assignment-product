package com.ingemark.assignment.products.rest.error.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public abstract class AbstractControllerAdvice {

  protected Mono<ResponseEntity<ErrorDto>> getErrorFromException(Throwable e) {
    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                   .body(ErrorDto.builder()
                                                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                 .message(e.getMessage())
                                                 .exceptionClass(e.getClass().getName())
                                                 .build()));
  }

}
