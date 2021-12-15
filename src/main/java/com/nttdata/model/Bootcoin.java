package com.nttdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "bootcoin")
public class Bootcoin {
  
  @Id
  private String id;
  
  @Field(name = "document_number")
  private String documentNumber;
  
  @Field(name = "cellphone_number")
  private String cellphoneNumber;
  
  @Field(name = "email")
  private String email;

  @Field(name = "quantity_coins")
  private Integer quantityCoins;

}
