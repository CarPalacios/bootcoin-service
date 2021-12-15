package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.model.Rate;
import com.nttdata.repository.RateRepository;
import com.nttdata.service.RateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RateServiceImpl implements RateService {
  
  @Autowired
  private RateRepository repo;

  @Override
  public Flux<Rate> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Rate> findById(String id) { 
    return repo.findById(id);
  }

  @Override
  public Mono<Rate> create(Rate t) {
    return null;
  }

  @Override
  public Mono<Rate> update(Rate t) {
    return repo.save(t);
  }

  @Override
  public Mono<Void> delete(String id) {
    return null;
  }

}
