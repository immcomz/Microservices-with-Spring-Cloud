package com.imm.microservices.limitservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imm.microservices.limitservices.bean.Limits;
import com.imm.microservices.limitservices.configuration.Configuration;

@RestController
public class LimitsController {
	
	//Dependency Injecting
	@Autowired
	private Configuration limitsConfiguration;
	
	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(limitsConfiguration.getMin(),limitsConfiguration.getMax());
		//get Limits values from Configuration properties/ Application properties
		//return new Limits(1,100);
	}

}
