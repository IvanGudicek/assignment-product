package com.ingemark.assignment.products.product.controller;

import com.ingemark.assignment.products.product.dto.ProductDto;
import com.ingemark.assignment.products.product.dto.ProductListDto;
import com.ingemark.assignment.products.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public Mono<ProductDto> save(@RequestBody ProductDto productDto) {
    return productService.createOrUpdate(productDto);
  }

  @PutMapping
  public Mono<ProductDto> update(@RequestBody ProductDto productDto) {
    return productService.createOrUpdate(productDto);
  }

  @GetMapping
  public Flux<ProductListDto> getAll() {
    return productService.findAll();
  }

  @GetMapping("/{productId}")
  public Mono<ProductDto> getById(@PathVariable String productId) {
    return productService.findById(Long.valueOf(productId));
  }

  @DeleteMapping("/{productId}")
  public Mono<ProductDto> deleteById(@PathVariable String productId) {
    return productService.deleteById(Long.valueOf(productId));
  }

}
