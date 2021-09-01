package com.example.purse.models.entities;

import java.util.List;

import com.example.purse.models.PaymentBootCoin;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootCoin {
    
    @Id
	private String id;
	
	private String typeIdentification;
	
	private String identification;
	
	private String phone;

    private String email;

    private List<PaymentBootCoin> listPayment;

}
