package com.ranjan.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjan.rest.webservices.restfulwebservices.user.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
