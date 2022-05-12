package com.kuhnenagel.service;

import com.kuhnenagel.model.Product;
import com.kuhnenagel.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ProductServiceTest {
    ProductRepo productRepo;
    ProductService productService;

    @BeforeEach
    public void setup() {
        productRepo = mock(ProductRepo.class);
        productService = new ProductService(productRepo);
    }

    @Test
    void productServiceTest() {
        productService.createProduct("some name", 123, 321);
        verify(productRepo, times(1))
                .save(new Product("some name", 123, 321));
    }
}