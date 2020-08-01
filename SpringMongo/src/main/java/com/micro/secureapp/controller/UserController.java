package com.micro.secureapp.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micro.secureapp.model.User;
import com.micro.secureapp.repo.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepo userRepository;

	private static final int DELAY_PER_ITEM_MS = 100;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Flux<User> getAllUsers() {
		System.out.println("Get all users");
		LOG.info("Getting all users.");
		Flux<User> users = userRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
		LOG.info("Users "+users);
		return users;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public Mono<User> getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		return userRepository.findById(userId);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Mono<String> addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user).map(g -> "Saved: " + g.getName());
	}

}
