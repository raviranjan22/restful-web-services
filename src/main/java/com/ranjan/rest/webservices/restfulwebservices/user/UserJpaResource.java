package com.ranjan.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.ranjan.rest.webservices.restfulwebservices.UserJpaRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserJpaRepository repository;
	
	public UserJpaResource(UserJpaRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
				
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
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		Optional<User> retrieveUser = repository.findById(id);
		if(retrieveUser == null)
			throw new UserNotFoundException("User not found");
		//Demonstration of HATEOAS (Hypermedia as the Engine of Application State) and HAL (JSON Hypertext Application Language)
		EntityModel<User> entityModel = EntityModel.of(retrieveUser.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
				
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Optional<User> retrieveUser = repository.findById(id);
		if(retrieveUser == null)
			throw new UserNotFoundException("No such user to delete");
		else
			repository.delete(retrieveUser.get());
	}

}
