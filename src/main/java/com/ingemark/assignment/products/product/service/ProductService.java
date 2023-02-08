package com.ingemark.assignment.products.product.service;

import com.ingemark.assignment.products.product.dto.ProductDto;
import com.ingemark.assignment.products.product.dto.ProductListDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<ProductDto> createOrUpdate(ProductDto productDto);

  Mono<ProductDto> deleteById(Long productId);

  Mono<ProductDto> findById(Long productId);

  Flux<ProductListDto> findAll();

}
