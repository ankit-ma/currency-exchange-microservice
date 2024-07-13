package com.ankit.webservice.configuration;

import java.util.HashMap;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouteLocator(RouteLocatorBuilder builder) {
		HashMap<String, String> loginInfoHashMap = new HashMap<>();
		loginInfoHashMap.put("isAdmin", "true");
		loginInfoHashMap.put("email", "ankitsharmauitbu@gmail.com");

		return builder.routes()
				.route(p -> p.path("/get")
						.filters(filter -> filter.addRequestHeader("username", "Ankit Kumar")
								.addRequestParameter("isAdmin", "true"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-new/**")
								.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
										"/currency-conversion-feign/${segment}"))
								.uri("lb://currency-conversion"))
				.build();
	}
}
