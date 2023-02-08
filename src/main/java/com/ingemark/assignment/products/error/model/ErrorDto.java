package com.ingemark.assignment.products.error.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDto {

  private String message;
  private String exceptionClass;
  private int statusCode;

}
