package com.scryng.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scryng.product.entities.Product;
import com.scryng.product.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Produto não cadastrado");
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void update(Product product, Long id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));

        p.setDescription(product.getDescription());
        p.setName(product.getName());
        p.setPrice(product.getPrice());

        repository.save(p);
    }
}
