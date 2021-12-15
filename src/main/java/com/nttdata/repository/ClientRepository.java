package com.nttdata.repository;

import com.nttdata.model.Client;

import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

//@NoRepositoryBean
/** El repositorio client hace una herencia a ReactiveMongoRepository. */
public interface ClientRepository 
extends ReactiveMongoRepository<Client, String> {
  
  Mono<Client> findByIdentityNumber(String identityNumber);
  
}
