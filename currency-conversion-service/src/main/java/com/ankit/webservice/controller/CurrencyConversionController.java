package com.ankit.webservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ankit.webservice.dto.CurrencyConvertion;
import com.ankit.webservice.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		HashMap<String, String> pathVariable = new HashMap<>();
		pathVariable.put("from", from);
		pathVariable.put("to", to);
		ResponseEntity<CurrencyConvertion> responseEntity = 
				new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}"
						,CurrencyConvertion.class
						, pathVariable);
		CurrencyConvertion resConvertion = responseEntity.getBody();
		return CurrencyConvertion.builder()
				.id(resConvertion.getId())
				.from(from)
				.to(to)
				.quantity(quantity)
				.conversionMultiple(resConvertion.getConversionMultiple())
				.totalCalculatedAmount(quantity.multiply(resConvertion.getConversionMultiple()))
				.environment(resConvertion.getEnvironment())
				.build();
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConvertion resConvertion = currencyExchangeProxy.retrieveExchangeValue(from, to);
		return CurrencyConvertion.builder()
				.id(resConvertion.getId())
				.from(from)
				.to(to)
				.quantity(quantity)
				.conversionMultiple(resConvertion.getConversionMultiple())
				.totalCalculatedAmount(quantity.multiply(resConvertion.getConversionMultiple()))
				.environment(resConvertion.getEnvironment())
				.build();
	}


}
