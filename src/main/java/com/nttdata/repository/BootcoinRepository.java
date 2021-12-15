package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.model.Bootcoin;

public interface BootcoinRepository 
extends ReactiveMongoRepository<Bootcoin, String> {

}
