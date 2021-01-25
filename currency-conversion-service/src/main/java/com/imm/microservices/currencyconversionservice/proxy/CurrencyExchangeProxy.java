package com.imm.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.imm.microservices.currencyconversionservice.bean.CurrencyConversion;

//set FeignClient to use proxy to currency-exchange-service and url
//@FeignClient(name="currency-exchange-service", url="localhost:8000")
//Now Feign Client will call the rest api from naming server
@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//CurrencyExchange rest api call from currencyConversion controller and
	//match response entity properties from exchange service to conversion service
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);

}
