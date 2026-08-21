package com.scryng.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scryng.product.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}