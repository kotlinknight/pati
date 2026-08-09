package com.pm.pati.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pm.pati.dto.ProductResponseDto;
import com.pm.pati.entity.Product;
import com.pm.pati.repository.ProductRepository;

@SpringBootTest
class ProductServiceCacheTest {

    @Autowired
    private ProductService productService;

    @MockitoBean
    private ProductRepository productRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        var cache = cacheManager.getCache("products");
        if (cache != null) {
            cache.clear();
        }
    }

    @Test
    void getProductById_CachingProvesOnlyOneDbCall() {
        Long productId = 1L;
        Product product = Product.builder()
                .id(productId)
                .name("Cached Laptop")
                .description("Cache testing")
                .price(new BigDecimal("1200.00"))
                .stockQuantity(5)
                .build();

        // Stub repository to return the product
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // First call: should miss cache and call repository
        ProductResponseDto firstCall = productService.getProductById(productId);
        // Second call: should hit cache and NOT call repository
        ProductResponseDto secondCall = productService.getProductById(productId);

        // Verify the results are correct
        assertEquals("Cached Laptop", firstCall.getName());
        assertEquals("Cached Laptop", secondCall.getName());

        // Verify finding by ID was only invoked once
        verify(productRepository, times(1)).findById(productId);
    }
}
