package com.nttdata.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.model.Bootcoin;
import com.nttdata.repository.BootcoinRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KafkaConsumer {
  
  @Autowired
  private BootcoinRepository bootcoinRepo;
  
  ObjectMapper objectMapper = new ObjectMapper();
  
  @KafkaListener(topics = "TOPIC-CLIENT", groupId = "group-bootcoin")
  public Disposable consume(String data) throws Exception {
      log.info("Consuming Message: {}", data);
      
      Bootcoin bootcoin = objectMapper.readValue(data, Bootcoin.class);
      
      return Mono.just(bootcoin)
          .log()
          .flatMap(bootcoinRepo::save)
          .subscribe();
  }
  
}
