package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.model.Account;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

  
}
