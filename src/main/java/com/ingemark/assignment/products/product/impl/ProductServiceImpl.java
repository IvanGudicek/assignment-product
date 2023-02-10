package com.ingemark.assignment.products.product.impl;

import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.infrastructure.database.ProductRepository;
import com.ingemark.assignment.products.product.ProductMapper;
import com.ingemark.assignment.products.product.ProductService;
import com.ingemark.assignment.products.rest.error.handling.ProductDuplicateCodeException;
import com.ingemark.assignment.products.rest.model.ProductDto;
import com.ingemark.assignment.products.rest.model.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CurrencyExchangeCalculationAdapter currencyExchangeCalculationAdapter;

  @Override
  public Mono<ProductDto> createOrUpdate(ProductDto productDto) {
    return currencyExchangeCalculationAdapter.getCurrencyPrice(productDto)
                                             .doOnNext(productDto::setPriceEur)
                                             .flatMap(product -> productRepository.save(productMapper.toEntity(productDto)))
                                             .onErrorResume(error -> Mono.error(new ProductDuplicateCodeException("Product with given code: "
                                                                                                                  + productDto.getCode()
                                                                                                                  + " already exist!")))
                                             .thenReturn(productDto);
  }

  @Override
  public Mono<Void> deleteById(Long productId) {
    return productRepository.deleteById(productId);
  }

  @Override
  @Transactional(readOnly = true)
  public Mono<ProductDto> findById(Long productId) {
    return productRepository.findById(productId).map(productMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Flux<ProductListDto> findAll() {
    return productRepository.findAll().map(productMapper::toListDto);
  }

}