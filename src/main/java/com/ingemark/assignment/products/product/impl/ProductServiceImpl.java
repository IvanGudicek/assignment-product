package com.ingemark.assignment.products.product.impl;

import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.infrastructure.database.ProductRepository;
import com.ingemark.assignment.products.product.ProductMapper;
import com.ingemark.assignment.products.product.ProductService;
import com.ingemark.assignment.products.rest.error.handling.ProductNotFoundException;
import com.ingemark.assignment.products.rest.model.ProductDto;
import com.ingemark.assignment.products.rest.model.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CurrencyExchangeCalculationAdapter currencyExchangeCalculationAdapter;
  private final TransactionTemplate transactionTemplate;

  @Override
  public Mono<ProductDto> createOrUpdate(ProductDto productDto) {

    transactionTemplate.setReadOnly(false);

    return transactionTemplate.execute(transaction -> currencyExchangeCalculationAdapter.getCurrencyPrice(productDto)
                                                                                        .doOnNext(productDto::setPriceEur)
                                                                                        .map(product -> productRepository.save(productMapper.toEntity(productDto)))
                                                                                        .map(productMapper::toDto));
  }

  @Override
  public Mono<ProductDto> deleteById(Long productId) {

    transactionTemplate.setReadOnly(false);

    return transactionTemplate.execute(transaction -> findById(productId)
      .doOnNext(product -> productRepository.deleteById(productId))
      .doOnError(error -> Mono.error(new ProductNotFoundException("Product with given id: " + productId + ", doesn't " + "exist!"))));
  }

  @Override
  public Mono<ProductDto> findById(Long productId) {

    transactionTemplate.setReadOnly(true);

    return transactionTemplate.execute(transaction ->
                                         Mono.just(productRepository.findById(productId))
                                             .map(product -> productMapper.toDto(product.get()))
                                             .onErrorResume(error -> Mono.error(new ProductNotFoundException("Product with given id: "
                                                                                                             + productId
                                                                                                             + ", doesn't "
                                                                                                             + "exist!"))));
  }

  @Override
  public Flux<ProductListDto> findAll() {

    transactionTemplate.setReadOnly(true);

    return transactionTemplate.execute(transaction ->
                                         Flux.fromIterable(productRepository.findAll()).map(productMapper::toListDto));
  }

}