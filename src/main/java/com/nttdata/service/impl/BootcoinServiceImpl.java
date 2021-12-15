package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Bootcoin;
import com.nttdata.repository.BootcoinRepository;
import com.nttdata.service.BootcoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootcoinServiceImpl implements BootcoinService {
  
  @Autowired
  private BootcoinRepository repo;
  
  @Override
  public Flux<Bootcoin> findAll(){
    return repo.findAll();
  }
    
  @Override
  public Mono<Bootcoin> create(Bootcoin bootcoin) {
    return repo.save(bootcoin);
  }

  @Override
  public Mono<Bootcoin> update(Bootcoin bootcoin) {
    return repo.save(bootcoin);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repo.deleteById(id);
  }

  @Override
  public Mono<Bootcoin> findById(String id) {
    return repo.findById(id);
  }
  
  
}
