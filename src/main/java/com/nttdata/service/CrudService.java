package com.nttdata.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T, K> {
  
  Flux<T> findAll();
  
  Mono<T> findById(K id);
  
  Mono<T> create(T t);
  
  Mono<T> update(T t);
  
  Mono<Void> delete(K id);
  

}
