package com.imm.microservices.currencyexchangeservice.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBrakerController {
	
	private Logger logger = 
				LoggerFactory.getLogger(CircuitBrakerController.class);
	
	@GetMapping("/sample-api")
	//any Failures retry and finally fallback to default api response
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String sampleApi() {
		logger.info("Sample api call received");
		//create a api get call for dummy end point
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", 
					String.class);
	return forEntity.getBody();
		
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
