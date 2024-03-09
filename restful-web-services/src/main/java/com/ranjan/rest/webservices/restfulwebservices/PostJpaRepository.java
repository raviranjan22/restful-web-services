package com.ranjan.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjan.rest.webservices.restfulwebservices.user.Post;

public interface PostJpaRepository extends JpaRepository<Post, Integer> {

}
