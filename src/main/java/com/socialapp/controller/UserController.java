package com.socialapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialapp.entity.User;
import com.socialapp.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/user/create")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		service.registerUser(user);
		return ResponseEntity.ok("User Registerd successfully");
	}

	@PatchMapping("/api/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer userId) {
		User updateUser = service.updateUser(user, userId);
		return ResponseEntity.ok(updateUser);
	}

	@GetMapping("/api/get/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		User user = service.findByUserId(userId);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/api/follow/{userId1}/{userId2}")
	public ResponseEntity<User> followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) {
		User user = service.followUser(userId1, userId2);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/api/search")
	public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query) {
		List<User> users = service.searchUser(query);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/api/getall")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userList = service.fetchAllUsers();
		return ResponseEntity.ok(userList);
	}

	@DeleteMapping("/api/remove/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		service.DeleteUser(id);
		return ResponseEntity.ok("User removed");
	}

}
