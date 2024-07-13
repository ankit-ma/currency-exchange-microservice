package com.ankit.webservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {
	
	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/circuit-breaker")
	@CircuitBreaker(name = "circuit-breaker", fallbackMethod  = "responseFallback")
	public String getCircuitBreakerResponse() {
		logger.info("Call is coming into circuit-breaker path");
		 ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/get", String.class);
		return "Call from circuit breaker";
	}

	private String responseFallback(Exception ex) {
		return "Fallback method";
		
	}
}
