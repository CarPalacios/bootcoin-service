package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.model.Rate;

public interface RateRepository extends ReactiveMongoRepository<Rate, String> {

}
