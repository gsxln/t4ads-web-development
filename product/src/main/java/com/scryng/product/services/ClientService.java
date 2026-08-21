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
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Cliente não cadastrado");
    }

    public Client save(Client client) {
        return repository.save(client);
    }

    public void update(Client client, Long id) {
        Client c = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));

        c.setName(client.getName());
        c.setAge(client.getAge());
        c.setEmail(client.getEmail());
        c.setPhone(client.getPhone());

        repository.save(c);
    }
}
