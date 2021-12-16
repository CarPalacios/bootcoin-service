package com.nttdata.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.model.Account;
import com.nttdata.model.Client;
import com.nttdata.repository.AccountRepository;
import com.nttdata.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KafkaConsumer {
  
  @Autowired
  private ClientRepository clientrepo;
  
  @Autowired
  private AccountRepository accountrepo;
  
  ObjectMapper objectMapper = new ObjectMapper();
  
  @KafkaListener(topics = "TOPIC-CLIENT", groupId = "group-bootcoin")
  public Disposable client(String data) throws Exception {
      log.info("Consuming Message: {}", data);
      
      Client client = objectMapper.readValue(data, Client.class);
      
      return Mono.just(client)
          .log()
          .flatMap(clientrepo::save)
          .subscribe();
  }
  
  @KafkaListener(topics = "TOPIC-ACCOUNT", groupId = "group-bootcoin")
  public Disposable account(String data) throws Exception {
      log.info("Consuming Message: {}", data);
      
      Account account = objectMapper.readValue(data, Account.class);
      
      return Mono.just(account)
          .log()
          .flatMap(accountrepo::save)
          .subscribe();
  }
}
