package com.ingemark.assignment.products.product.service.impl;

import com.ingemark.assignment.products.hnb.adapter.CurrencyExchangeCalculationAdapter;
import com.ingemark.assignment.products.hnb.adapter.impl.CurrencyExchangeCalculationAdapterImpl;
import com.ingemark.assignment.products.hnb.service.CurrencyExchangeCalculationService;
import com.ingemark.assignment.products.product.dao.ProductRepository;
import com.ingemark.assignment.products.product.dto.ProductDto;
import com.ingemark.assignment.products.product.dto.ProductListDto;
import com.ingemark.assignment.products.product.error.exception.ProductNotFoundException;
import com.ingemark.assignment.products.product.mapper.ProductMapper;
import com.ingemark.assignment.products.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CurrencyExchangeCalculationService currencyExchangeCalculationService;

  @Override
  public Mono<ProductDto> createOrUpdate(ProductDto productDto) {

    getCalculatedCurrencyPrice(productDto).subscribe(productDto::setPriceEur);

    return Mono.just(productRepository.save(productMapper.toEntity(productDto)))
               .map(productMapper::toDto);
  }

  @Override
  public Mono<ProductDto> deleteById(Long productId) {

    //    Optional<Product> productOptional = productRepository.findById(productId);
    //    if(productOptional.isPresent()){
    //      productRepository.deleteById(productId);
    //    }

    return findById(productId)
      .doOnNext(product -> productRepository.deleteById(productId))
      .doOnError(error -> Mono.error(new ProductNotFoundException("Product with given id: " + productId + ", doesn't " + "exist!")));
  }

  @Override
  @Transactional(readOnly = true)
  public Mono<ProductDto> findById(Long productId) {
    return Mono.just(productRepository.findById(productId))
               .map(product -> productMapper.toDto(product.get()))
               .onErrorResume(error -> Mono.error(new ProductNotFoundException("Product with given id: " + productId + ", doesn't " + "exist!")));

  }

  @Override
  @Transactional(readOnly = true)
  public Flux<ProductListDto> findAll() {
    return Flux.fromIterable(productRepository.findAll()).map(productMapper::toListDto);
  }

  private Mono<BigDecimal> getCalculatedCurrencyPrice(ProductDto productDto) {
    CurrencyExchangeCalculationAdapter currencyExchangeCalculationAdapter = new CurrencyExchangeCalculationAdapterImpl(productDto,
                                                                                                                       currencyExchangeCalculationService);
    return currencyExchangeCalculationAdapter.getCurrencyPrice();
  }

}