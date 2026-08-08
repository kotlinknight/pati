package com.pm.pati.service;

import java.util.List;

import com.pm.pati.dto.ProductRequestDto;
import com.pm.pati.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto dto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    void deleteProduct(Long id);
}
