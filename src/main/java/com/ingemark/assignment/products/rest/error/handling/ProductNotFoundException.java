package com.ingemark.assignment.products.rest.error.handling;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(message);
  }

}
