package com.jay.restfulwebservices.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jay.restfulwebservices.exception.UserNotFoundException;
import com.jay.restfulwebservices.model.User;
import com.jay.restfulwebservices.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable String id) {
		User user = userService.getUser(Integer.parseInt(id));
		if(user == null) {
			throw new UserNotFoundException("User not found with id = " + id);
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> addUsers(@RequestBody User user) {
		User savedUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
