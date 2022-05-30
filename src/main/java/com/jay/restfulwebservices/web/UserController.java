package com.jay.restfulwebservices.web;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jay.restfulwebservices.exception.UserNotFoundException;
import com.jay.restfulwebservices.model.User;
import com.jay.restfulwebservices.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable String id) {
		User user = userService.getUser(Integer.parseInt(id));
		if (user == null) {
			throw new UserNotFoundException("User not found with id = " + id);
		}
		EntityModel<User> model = EntityModel.of(user);
		model.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.getUsers()).withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> addUsers(@Valid @RequestBody User user) {
		User savedUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable String id) {
		User user = userService.deleteUser(Integer.parseInt(id));
		if (user == null) {
			throw new UserNotFoundException("User not found with id = " + id);
		}
	}
	
	/*Internationalization*/
	@GetMapping("/hello-world")
	public String helloWorld(@RequestHeader(name = "Accept-Language", required =  false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null,"Default message",locale);
	}
}
