package com.scryng.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scryng.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}