package com.ankit.webservice.limitsservice.Bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Limits {
	private int minimum;
	private int maximun;
}
