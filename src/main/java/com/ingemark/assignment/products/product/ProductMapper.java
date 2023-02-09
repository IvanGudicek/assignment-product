package com.ingemark.assignment.products.product;

import com.ingemark.assignment.products.rest.model.ProductDto;
import com.ingemark.assignment.products.rest.model.ProductListDto;
import com.ingemark.assignment.products.infrastructure.database.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toDto(Product product);

  ProductListDto toListDto(Product product);

  Product toEntity(ProductDto productDto);

}
