package com.ingemark.assignment.products.rest.error.handling;

public class ProductDuplicateCodeException extends RuntimeException {

  public ProductDuplicateCodeException(String message) {
    super(message);
  }

}
