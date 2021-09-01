package com.example.purse.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentBootCoin {
    
    private String phoneTransmitter;

    private String phoneReceiver;
    
    private Double amount;

    private String type;
    

}
