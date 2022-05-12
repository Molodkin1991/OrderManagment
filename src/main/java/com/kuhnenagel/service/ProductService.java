package com.kuhnenagel.service;

import com.kuhnenagel.model.Product;
import com.kuhnenagel.repo.ProductRepo;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(String nameOfProduct,Integer skuCode,Integer unitPrice){
        Product product = new Product();
        product.setName(nameOfProduct);
        product.setSkuCode(skuCode);
        product.setUnitPrice(unitPrice);
        return productRepo.save(product);
    }
}
