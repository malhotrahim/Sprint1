package com.cg.ima;

import org.springframework.boot.builder.SpringApplicationBuilder;



public class ServletInitializer {
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		   return builder.sources(SpringDataMain.class);
}}
