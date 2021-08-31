package com.example.purse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.example.purse.handler.PurseHandler;

@Configuration
public class RouterConfig {
	
	@Bean
	public RouterFunction<ServerResponse> rutas(PurseHandler handler) {
		return route(GET("/purse"), handler::findAll)
				.andRoute(GET("/debit/{id}"), handler::findById)
				.andRoute(POST("/purse"), handler::save)
				.andRoute(POST("/purse/update"), handler::update);
				//.andRoute(POST("/purse/update"), handler::update);
	}
	
}
