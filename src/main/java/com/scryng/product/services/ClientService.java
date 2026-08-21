package com.scryng.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scryng.product.entities.Client;
import com.scryng.product.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
    
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
