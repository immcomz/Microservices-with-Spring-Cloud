package com.imm.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imm.microservices.currencyexchangeservice.bean.CurrencyExchange;

//import com.imm.microservices.currencyexchangeservice.bean.CurrencyExchange;

public interface CurrencyExchangeRepository 
extends JpaRepository<CurrencyExchange,Long>{
	//java JPA data will convert this method name to a sql query "findByFromAndTo"
	CurrencyExchange findByFromAndTo(String from,String to);

}
