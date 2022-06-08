package com.jay.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.restfulwebservices.model.Posts;

public interface PostsRepository extends JpaRepository<Posts, Integer>{

}
