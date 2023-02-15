package com.ingemark.assignment.products.product;

import com.ingemark.assignment.products.infrastructure.adapter.hnb.impl.CurrencyExchangeCalculationAdapterImpl;
import com.ingemark.assignment.products.infrastructure.database.Product;
import com.ingemark.assignment.products.infrastructure.database.ProductRepository;
import com.ingemark.assignment.products.product.impl.ProductServiceImpl;
import com.ingemark.assignment.products.rest.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

  private ProductServiceImpl productService;

  private ProductMapper productMapper;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private CurrencyExchangeCalculationAdapterImpl currencyExchangeCalculationAdapterImpl;

  @BeforeEach
  public void setup() {
    this.productMapper = new ProductMapperImpl();
    this.productService = new ProductServiceImpl(productRepository, productMapper, currencyExchangeCalculationAdapterImpl);
  }

  @Test
  public void should_retrieveProduct_when_productIdIsGiven() {

    // given
    Long productId = 1l;

    Mockito.when(productRepository.findById(1L)).thenReturn(Mono.just(Product.builder().id(1L).name("Book").description("Book description").code(
      "3339999333").priceHrk(new BigDecimal("896")).priceEur(new BigDecimal("18.4567")).available(true).build()));

    // when
    Mono<ProductDto> productDtoMono = this.productService.findById(productId);

    // then
    ProductDto expectedProduct =
      ProductDto.builder()
                .name("Book")
                .description("Book description")
                .code("3339999333")
                .priceHrk(new BigDecimal("896"))
                .priceEur(new BigDecimal("18.4567"))
                .available(true)
                .build();

    StepVerifier.create(productDtoMono)
                .expectNextMatches(actualProductDto -> expectedProduct.getName().equals(actualProductDto.getName()) &&
                                                       expectedProduct.getDescription().equals(actualProductDto.getDescription()) &&
                                                       expectedProduct.getCode().equals(actualProductDto.getCode()))
                .expectComplete()
                .verify();
  }

}
