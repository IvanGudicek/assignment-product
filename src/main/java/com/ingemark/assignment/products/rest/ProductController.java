package com.ingemark.assignment.products.rest;

import com.ingemark.assignment.products.product.ProductService;
import com.ingemark.assignment.products.rest.model.ProductDto;
import com.ingemark.assignment.products.rest.model.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Mono<ProductDto>> save(@Valid @RequestBody ProductDto productDto) {
    return ResponseEntity.ok(productService.createOrUpdate(productDto));
  }

  @PutMapping
  public ResponseEntity<Mono<ProductDto>> update(@Valid @RequestBody ProductDto productDto) {
    return ResponseEntity.ok(productService.createOrUpdate(productDto));
  }

  @GetMapping
  public ResponseEntity<Flux<ProductListDto>> getAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Mono<ProductDto>> getById(@PathVariable String productId) {
    return ResponseEntity.ok(productService.findById(Long.valueOf(productId)));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Mono<Void>> deleteById(@PathVariable String productId) {
    return ResponseEntity.ok(productService.deleteById(Long.valueOf(productId)));
  }

}
