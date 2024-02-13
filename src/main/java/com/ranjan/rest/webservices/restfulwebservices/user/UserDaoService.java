package com.ranjan.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static int userId=0;
	
	static {
		users.add(new User(++userId, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++userId, "Eve", LocalDate.now().minusYears(24)));
		users.add(new User(++userId, "Jim", LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll(){
		return users;
	}

	public User retrieveUser(int id) {
		//return users.get(id-1);
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		//return users.stream().filter(predicate).findFirst().get();
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++userId);
		users.add(user);
		return user;
	}
}
