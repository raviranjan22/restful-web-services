package com.ranjan.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	//URI Versioning
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Ravi Ranjan");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Ravi", "Ranjan"));
	}

	//Parameters Versioning
	@GetMapping(path= "/person", params = "version=1")
	public PersonV1 getPersonV1Param() {
		return new PersonV1("Ravi Ranjan");
	}
	
	@GetMapping(path= "/person", params = "version=2")
	public PersonV2 getPersonV2Param() {
		return new PersonV2(new Name("Ravi", "Ranjan"));
	}
	
	//Headers Versioning
	@GetMapping(path= "/person", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1Header() {
		return new PersonV1("Ravi Ranjan");
	}

	@GetMapping(path= "/person", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2Header() {
		return new PersonV2(new Name("Ravi", "Ranjan"));
	}
	
	//Media Type Versioning
	@GetMapping(path= "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonV1MediaType() {
		return new PersonV1("Ravi Ranjan");
	}

	@GetMapping(path= "/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonV2MediaType() {
		return new PersonV2(new Name("Ravi", "Ranjan"));
	}
}
