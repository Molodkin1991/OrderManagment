package com.kuhnenagel.repo;

import com.kuhnenagel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long> {
}
