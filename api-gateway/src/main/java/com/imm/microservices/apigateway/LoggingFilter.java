package com.imm.microservices.apigateway;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{
	
	//This is the place to Authentications and Authorizations shoild be implemented

	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		//get the server request exchange point/Path of the request received
		logger.info("Path of the request received -> {}", 
				exchange.getRequest().getPath());
		
		return chain.filter(exchange);
	}

}
