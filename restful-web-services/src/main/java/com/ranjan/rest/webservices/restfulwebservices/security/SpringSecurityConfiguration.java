package com.ranjan.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//1. All requests should be authorized
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		//2. If requests are not authenticated then a basic authetication pop-up is shown
		http.httpBasic(withDefaults());
		
		//Disable CSRF for POST and PUT
		http.csrf(csrf -> csrf.disable()); 
		
		return http.build();
	}

}
