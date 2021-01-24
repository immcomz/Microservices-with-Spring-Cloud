package com.imm.microservices.limitservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imm.microservices.limitservices.bean.Limits;

@RestController
public class LimitsController {
	
	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(1,100);
	}

}
