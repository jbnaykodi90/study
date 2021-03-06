package com.jay.restfulwebservices.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jay.restfulwebservices.model.SomeBean;

@RestController
public class FilteringController {
	@GetMapping("/filtering-bean")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("val1", "val2", "val3");
		//Provide values which needs to be sent in response
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		//Provide filter name given with @JsonFilter annotation on model class
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
}
