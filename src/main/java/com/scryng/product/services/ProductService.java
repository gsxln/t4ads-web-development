package com.scryng.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scryng.product.entities.Product;
import com.scryng.product.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
