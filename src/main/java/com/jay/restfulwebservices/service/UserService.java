package com.jay.restfulwebservices.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jay.restfulwebservices.model.User;

@Service
public class UserService {
	private static List<User> userList = new ArrayList<>();

	static {
		userList.add(new User(1, "Jay"));
		userList.add(new User(2, "Arch"));
		userList.add(new User(3, "Ridhu"));
	}

	public List<User> getUsers() {
		return userList;
	}

	public User getUser(int id) {
		for (User eachUser : userList) {
			if (eachUser.getId() == id) {
				return eachUser;
			}
		}
		return null;
	}

	public User addUser(User user) {
		userList.add(user);
		return user;
	}

	public User deleteUser(int id) {
		Iterator<User> iterator = userList.iterator();
		while (iterator.hasNext()) {
			User eachUser = iterator.next();
			if (eachUser.getId() == id) {
				iterator.remove();
				return eachUser;
			}
		}
		return null;
	}
}
