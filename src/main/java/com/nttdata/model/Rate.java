package com.nttdata.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Rate {
  
  @Id
  private String id;
  
  private Double purchaseRate;
  
  private Double saleRate;

}
