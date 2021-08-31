package com.example.purse.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.example.purse.models.entities.Purse;
import com.example.purse.services.IPurseService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PurseHandler {

	private final IPurseService purseService;
	
	@Autowired
	public PurseHandler(IPurseService purseService) {
		this.purseService = purseService;
	}
	
	
	public Mono<ServerResponse> findAll(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
					.body(purseService.findAll(), Purse.class);
	}
	
	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("purseId");
		return purseService.findById(id).flatMap(p -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue(p))
				.switchIfEmpty(Mono.error(new RuntimeException("Purse Id card not found")));
	}
	
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<Purse> purseRequest = request.bodyToMono(Purse.class);
		return purseRequest.flatMap(p -> {
						p.setAmount(0.0);
						return Mono.just(p);
					})
					.flatMap(purseService::create)
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
	
	public Mono<ServerResponse> update(ServerRequest request) {
		Mono<Purse> purse = request.bodyToMono(Purse.class);
		return purse.flatMap(p -> {
			return purseService.update(p);
		})
		.flatMap( p -> ServerResponse.created(URI.create("/purse/".concat(p.getId())))
				.bodyValue(p)
				);
	}

}
