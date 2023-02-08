package com.ingemark.assignment.products.error;

import com.ingemark.assignment.products.error.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public abstract class AbstractControllerAdvice {

  protected Mono<ResponseEntity<ErrorDto>> getErrorFromException(Throwable e, String message) {
    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                   .body(ErrorDto.builder()
                                                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                 .message(message != null ? message : e.getMessage())
                                                 .exceptionClass(e.getClass().getName())
                                                 .build()));
  }

}
