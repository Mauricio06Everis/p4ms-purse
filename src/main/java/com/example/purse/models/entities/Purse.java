package com.example.purse.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "purse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purse {

	@Id
	private String id;
	
	private String typeIdentification;
	
	private String identification;
	
	private String phone;
	
	private String imei;
	
	private String email;
	
	private String cardNumber;

	private Double amount;
	
}
