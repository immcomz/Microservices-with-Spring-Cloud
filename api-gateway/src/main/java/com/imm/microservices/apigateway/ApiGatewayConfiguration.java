package com.imm.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//to create custom routes with spring cloud gateway
@Configuration //To explicitly define Beans
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				
				//any request from http://get redirect to "http://httpbin.org:80
				//with custom header 
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				
				//http://localhost:8765/currency-exchange/from/USD/to/INR
				//any request url start from http://currency-exchange/**
				//redirect to eureka naming server with load balancing(currency-exchange-service)
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange-service"))
				
				//http://localhost:8765/currency-converter/from/USD/to/INR/quantity/1000
				//any request url start from http://currency-conversion/**
				//redirect to eureka naming server with load balancing(currency-conversion)
				.route(p -> p.path("/currency-converter/**")
						.uri("lb://currency-conversion"))
				
				//http://localhost:8765/currency-converter-feign/from/USD/to/INR/quantity/1000
				//any request url start from http://currency-conversion-feign/**
				//redirect to eureka naming server with load balancing
				.route(p -> p.path("/currency-converter-feign/**")
						.uri("lb://currency-conversion"))
				
				//any request url start from http:///currency-conversion-new/**
				//replace the url with /currency-conversion-feign/ and 
				//redirect to eureka naming server with load balancing
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				
				.build();
	}

}
