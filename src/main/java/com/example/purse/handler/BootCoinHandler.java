package com.example.purse.handler;

import java.net.URI;

import com.example.purse.models.PaymentBootCoin;
import com.example.purse.models.entities.BootCoin;
import com.example.purse.services.IBootCoinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BootCoinHandler {
    
    private final IBootCoinService bootCoinService;

    private final Double COMISION = 3.0;

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    public BootCoinHandler(IBootCoinService iBootCoinService) {
        this.bootCoinService = iBootCoinService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                            .body(bootCoinService.findAll(), BootCoin.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("bootCoinId");
        return bootCoinService.findById(id).flatMap(p -> ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(p))
                        .switchIfEmpty(Mono.error(new RuntimeException("Boot coint id card not found")));
        
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<BootCoin> bootRequest = request.bodyToMono(BootCoin.class);
        return bootRequest.flatMap(bootCoinService::create)
                        .flatMap(p -> ServerResponse.created(URI.create("/debit/".concat(p.getId())))
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(p))
                        .onErrorResume(error -> {
                            WebClientResponseException errorResponse = (WebClientResponseException) error;
                            if(errorResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                                return ServerResponse.badRequest()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(errorResponse.getResponseBodyAsString());
                            }
                            return Mono.error(errorResponse);
                        });
        
    }

    public Mono<ServerResponse> createBuyBootCoin(ServerRequest request) {
        Mono<PaymentBootCoin> product = request.bodyToMono(PaymentBootCoin.class);
        return product.flatMap(p -> {
            
            kafkaTemplate.send("TOPIC_BOOTCOIN","BOOTCOINT",p);
            return ServerResponse.ok().body(product,PaymentBootCoin.class).switchIfEmpty(ServerResponse.noContent().build());
        });
    }

}
