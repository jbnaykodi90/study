package com.jay.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.restfulwebservices.model.Posts;
import com.jay.restfulwebservices.model.User;
import com.jay.restfulwebservices.repository.PostsRepository;
import com.jay.restfulwebservices.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostsRepository postsRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(int id) {
		return userRepository.findById(id);
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(String id) {
		userRepository.deleteById(Integer.parseInt(id));
	}
	
	public Posts addPost(Posts post) {
		return postsRepository.save(post);
	}
}
