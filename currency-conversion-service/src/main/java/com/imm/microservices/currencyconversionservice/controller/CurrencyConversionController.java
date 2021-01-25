package com.imm.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.imm.microservices.currencyconversionservice.bean.CurrencyConversion;

@RestController
public class CurrencyConversionController {
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		//create HashMap to store path variables from and to which will create a rest Template
		//to make rest api call to currency Exchange Service
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to",to);
		
		// create RestTemplate to make a Rest api call to currencyExchange Service and match it to CurrencyConversion
		 ResponseEntity <CurrencyConversion> responseEntity = new RestTemplate().getForEntity
		 ("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class,uriVariables);
		 
		 // get the response body from response entity
		 CurrencyConversion currencyConversion = responseEntity.getBody();
		 currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		 
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,
				currencyConversion.getConversionMultiple(),
				currencyConversion.getTotalCalculatedAmount(),currencyConversion.getEnvironment());
		 
	}

}
