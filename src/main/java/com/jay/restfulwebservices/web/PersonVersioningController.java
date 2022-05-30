package com.jay.restfulwebservices.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.restfulwebservices.model.Name;
import com.jay.restfulwebservices.model.PersonV1;
import com.jay.restfulwebservices.model.PersonV2;

@RestController
public class PersonVersioningController {

	@GetMapping("/ver1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Jay");
	}

	@GetMapping("/ver2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Jay", "Naykodi"));
	}

	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Jay");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("Jay", "Naykodi"));
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Jay");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("Jay", "Naykodi"));
	}

	@GetMapping(value = "/person/producer", produces = "application/com.jay.app-v1+json")
	public PersonV1 getPersonProducerV1() {
		return new PersonV1("Jay");
	}

	@GetMapping(value = "/person/producer", produces = "application/com.jay.app-v2+json")
	public PersonV2 getPersonProducerV2() {
		return new PersonV2(new Name("Jay", "Naykodi"));
	}
}
