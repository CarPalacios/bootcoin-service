package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.model.Bootcoin;

//@NoRepositoryBean
@Repository
public interface BootcoinRepository 
extends ReactiveMongoRepository<Bootcoin, String> {

}
