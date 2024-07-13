package com.ankit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.webservice.entity.CurrencyExcahnge;
import com.ankit.webservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExcahnge retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExcahnge currencyExchange =repository.findByFromAndTo(from, to);
		if(null == currencyExchange) {
			throw new RuntimeException("Unable to find data for "+from +" to "+to);
		}
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		return currencyExchange;
	}

}
