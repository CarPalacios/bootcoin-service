package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.model.Account;

import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

  
}
