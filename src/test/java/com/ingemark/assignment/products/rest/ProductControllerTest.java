package com.ingemark.assignment.products.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.ingemark.assignment.products.infrastructure.adapter.hnb.CurrencyExchangeDto;
import com.ingemark.assignment.products.rest.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "100000")
public class ProductControllerTest {

  private ObjectMapper mapper;

  private WireMockServer wireMockServer;

  @Autowired
  private WebTestClient webClient;

  @BeforeEach
  public void setup() {
    this.mapper = new ObjectMapper();
  }

  @Test
  public void should_saveProduct_when_productDtoIsGiven() {

    this.wireMockServer = new WireMockServer(options().dynamicPort());
    this.wireMockServer.start();

    // given
    ProductDto productDto =
      ProductDto.builder().name("T-shirt").description("T-shirt description").code("5559999555").priceHrk(new BigDecimal("79")).available(true).build();

    wireMockServer.givenThat(
      get(urlEqualTo("https://api.hnb.hr/tecajn-eur/v3"))
        .withQueryParam("valuta", equalTo("DKK"))
        .willReturn(
          aResponse()
            .withBody(successBodyWithCurrencies())));

    // when
    webClient.post()
             .uri("/products")
             .body(BodyInserters.fromValue(productDto))
             .exchange()
             // then
             .expectStatus().isOk()
             .expectBody()
             .jsonPath("$.name").isEqualTo("T-shirt")
             .jsonPath("$.description").isEqualTo("T-shirt description")
             .jsonPath("$.code").isEqualTo("5559999555")
             .jsonPath("$.priceHrk").isEqualTo("79")
             .jsonPath("$.priceEur").isEqualTo("10.618")
             .jsonPath("$.available").isEqualTo(true);

    // delete after product is inserted
    webClient.delete()
             .uri("/products/{productId}", 6)
             .exchange()
             // then
             .expectStatus().isOk()
             .expectBody()
             .isEmpty();

    this.wireMockServer.stop();
  }

  @Test
  public void should_retrieveFirstProduct_when_productIdIsGiven() {

    // given
    String productId = "1";

    // when
    webClient.get()
             .uri("/products/{productId}", productId)
             .exchange()
             // then
             .expectStatus().isOk()
             .expectBody()
             .jsonPath("$.id").isEqualTo(1)
             .jsonPath("$.name").isEqualTo("Tape")
             .jsonPath("$.description").isEqualTo("Tape description")
             .jsonPath("$.code").isEqualTo("1234567891")
             .jsonPath("$.priceHrk").isEqualTo("37.5")
             .jsonPath("$.priceEur").isEqualTo("5.0")
             .jsonPath("$.available").isEqualTo(true);
  }

  @Test
  public void should_deleteLastProduct_when_productIdIsGiven() {

    // given
    String productId = "5";

    // before deleting, must be 5 products
    webClient.get()
             .uri("/products")
             .exchange()
             // then
             .expectStatus().isOk()
             .expectBody()
             .jsonPath("$.length()").isEqualTo(5)
             .jsonPath("$[0].id").isEqualTo(1)
             .jsonPath("$[1].id").isEqualTo(2)
             .jsonPath("$[2].id").isEqualTo(3)
             .jsonPath("$[3].id").isEqualTo(4)
             .jsonPath("$[4].id").isEqualTo(5);

    // when
    webClient.delete()
             .uri("/products/{productId}", productId)
             .exchange()
             // then
             .expectStatus().isOk()
             .expectBody()
             .isEmpty();

    // after deleting, must be 4 products
    webClient.get()
             .uri("/products")
             .exchange()
             // then
             .expectStatus().isOk()
             .expectBody()
             .jsonPath("$.length()").isEqualTo(4)
             .jsonPath("$[0].id").isEqualTo(1)
             .jsonPath("$[1].id").isEqualTo(2)
             .jsonPath("$[2].id").isEqualTo(3)
             .jsonPath("$[3].id").isEqualTo(4);
  }

  private String successBodyWithCurrencies() {
    List<CurrencyExchangeDto> currencies = new ArrayList<>();
    currencies.add(CurrencyExchangeDto.builder().broj_tecajnice("28")
                                      .datum_primjene("2023-02-08")
                                      .drzava("Danska")
                                      .drzava_iso("DNK")
                                      .sifra_valute("208")
                                      .valuta("DKK")
                                      .kupovni_tecaj("7,4527")
                                      .srednji_tecaj("7,4415")
                                      .prodajni_tecaj("7,4303")
                                      .build());
    try {
      return mapper.writeValueAsString(currencies);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

}
