package com.jay.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jay.restfulwebservices.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
