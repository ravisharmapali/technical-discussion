package com.cdac.services;

import java.util.List;

import com.cdac.payloads.UserDto;

public interface UserService {
	
	//method to create user
	 UserDto createUser(UserDto user);					
	 
	// method to update user , and int id bcoz which userid you want to update
	 UserDto updateUser(UserDto user,Integer userId);	
	 
	//get user by id
	 UserDto getUserById(Integer userId);
	 
	//to get all the users 
	 List<UserDto> getAllUsers();
	 
	// to delete user
	 void deleteUser(Integer userId);
	 
	 
}
