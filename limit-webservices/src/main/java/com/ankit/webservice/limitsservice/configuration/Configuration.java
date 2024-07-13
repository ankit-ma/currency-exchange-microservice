package com.ankit.webservice.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties("limits-service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Configuration{
	
	private int minimum;
	private int maximum;
	
}