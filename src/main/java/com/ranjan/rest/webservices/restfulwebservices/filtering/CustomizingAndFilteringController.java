package com.ranjan.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomizingAndFilteringController {
	
	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("Value1", "Value2", "Value3");
	}

}
