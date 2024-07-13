package com.ankit.webservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.webservice.limitsservice.Bean.Limits;
import com.ankit.webservice.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits getLimits() {
		Limits limits = Limits.builder()
				.minimum(configuration.getMinimum())
				.maximun(configuration.getMaximum())
				.build();
		return limits;
	}

}
