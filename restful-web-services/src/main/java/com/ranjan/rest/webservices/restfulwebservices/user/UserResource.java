package com.ranjan.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	public UserResource(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userDaoService.findAll();
				
	}
	
	//Demonstration of HATEOAS
	/*
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		//return userDaoService.retrieveUser(id);
		User retrieveUser = userDaoService.retrieveUser(id);
		if(retrieveUser == null)
			throw new UserNotFoundException("User not found");
		return retrieveUser;
				
	}
	*/
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		//return userDaoService.retrieveUser(id);
		User retrieveUser = userDaoService.retrieveUser(id);
		if(retrieveUser == null)
			throw new UserNotFoundException("User not found");
		//Demonstration of HATEOAS (Hypermedia as the Engine of Application State) and HAL (JSON Hypertext Application Language)
		EntityModel<User> entityModel = EntityModel.of(retrieveUser);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
				
	}
	
	/*
	@PostMapping("/users")
	public void postUser(@RequestBody User user) {
		userDaoService.save(user);
	}
	*/
	
	/*
	@PostMapping("/users")
	public ResponseEntity<User> postUser(@RequestBody User user) {
		userDaoService.save(user);
		return ResponseEntity.created(null).build();
	}
	*/
	
	@PostMapping("/users")
	public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User retrieveUser = userDaoService.retrieveUser(id);
		if(retrieveUser == null)
			throw new UserNotFoundException("No such user to delete");
		else
			userDaoService.deleteUser(retrieveUser.getId());
	}

}
