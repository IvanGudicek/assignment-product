package com.ingemark.assignment.products.product.mapper;

import com.ingemark.assignment.products.product.dto.ProductDto;
import com.ingemark.assignment.products.product.dto.ProductListDto;
import com.ingemark.assignment.products.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toDto(Product product);

  ProductListDto toListDto(Product product);

  Product toEntity(ProductDto productDto);

}
