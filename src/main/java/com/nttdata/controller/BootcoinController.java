package com.nttdata.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Bootcoin;
import com.nttdata.model.Rate;
import com.nttdata.service.BootcoinService;
import com.nttdata.service.RateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/bootcoin")
public class BootcoinController {
  
  @Autowired
  private BootcoinService service;

  @Autowired
  private RateService rateService;
  
  @GetMapping
  public Mono<ResponseEntity<Flux<Bootcoin>>> findAll() {
    
    Flux<Bootcoin> bootcoin = service.findAll();
    return Mono.just(ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(bootcoin));
    
  }
  
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Bootcoin>> findById(@PathVariable("id") String id) {
    
    return service.findById(id)
        .map(c -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));
  }
  
  @PostMapping
  public Mono<ResponseEntity<Bootcoin>> 
    create(@RequestBody Bootcoin bootcoin, final ServerHttpRequest request) {

    return service.create(bootcoin)
    .map(createdObject -> {
      return ResponseEntity
          .created(URI.create(request.getURI().toString().concat(createdObject.getId())))
              .contentType(MediaType.APPLICATION_JSON)
              .body(createdObject);
    });
}
  
  
  @PutMapping("/id")
  public Mono<ResponseEntity<Bootcoin>> update(@PathVariable String id, @RequestBody Bootcoin bootcoin) {
    
    Mono<Bootcoin> monoDatabase = service.findById(id);
    
    Mono<Bootcoin> monoBootcoin = Mono.just(bootcoin);
    
    return monoDatabase
        .zipWith(monoBootcoin, (db, bc) -> {
          db.setId(id);
          db.setDocumentNumber(bc.getDocumentNumber());
          db.setCellphoneNumber(bc.getCellphoneNumber());
          db.setEmail(bc.getEmail());
          db.setQuantityCoins(bc.getQuantityCoins());
          return db;
        })
        .flatMap(service::update)
        .map(c -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(c))
        .defaultIfEmpty(new ResponseEntity<Bootcoin>(HttpStatus.NOT_FOUND));
        
  }
  
  @PutMapping("/rate")
  public Mono<ResponseEntity<Rate>> update(@PathVariable String id, @RequestBody Rate rate) {
    
    Mono<Rate> monoDatabase = rateService.findById(id);
    
    Mono<Rate> monoRate = Mono.just(rate);
    
    return monoDatabase
        .zipWith(monoRate, (db, mr) -> {
          db.setId(id);
          db.setPurchaseRate(mr.getPurchaseRate());
          db.setSaleRate(mr.getSaleRate());
          return db;
        })
        .flatMap(rateService::update)
        .map(c -> ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(c))
        .defaultIfEmpty(new ResponseEntity<Rate>(HttpStatus.NOT_FOUND));
    
  } 
  
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") String id) {
    return service.findById(id)
        .flatMap(c -> {
          return service.delete(c.getId())
              .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
        })
        .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }

}
