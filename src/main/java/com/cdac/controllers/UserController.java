package com.cdac.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.payloads.UserDto;
import com.cdac.services.UserService;

import jakarta.validation.Valid;

/**
 * The UserController class is associated with the User module.
 * @author Ravi Sharma
 * 
 * */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	// service to call business logic
	@Autowired
	private UserService userService;

	// to create new user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		System.out.println("inside createUser() ");
		UserDto createdUserDto = this.userService.createUser(userDto);

		// sending response code
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}

	// to update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {

		UserDto updatedUser = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}

	// to delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer uId) {
		userService.deleteUser(uId);
		//
		return new ResponseEntity(Map.of("message", "User deleted successfully"), HttpStatus.OK);
	}

	// to get all the users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		// returning list of users
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	// returns specific user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		// returning single user from id
		return ResponseEntity.ok(this.userService.getUserById(userId));

	}

}
