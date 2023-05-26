package com.cdac.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entities.User;
import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.payloads.UserDto;
import com.cdac.repositories.UserRepo;
import com.cdac.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	// to perform various User related operations

	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
//	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		System.out.println("inside createUser method");
		// converting dto to user obj
//		User user = this.dtoToUser(userDto);
		
		User user = this.modelMapper.map(userDto, User.class);
		
		User savedUser = this.userRepo.save(user);

		// conv user to dto obj
//		return this.userToDto(savedUser);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// fetching user object from database
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

		// updating the user with supplied useDto data
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserAbout(userDto.getUserAbout());

		// saving this updated user
		User updatedUser = this.userRepo.save(user);

		UserDto userDto2 = this.userToDto(updatedUser);

		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// find the user or throw exception 
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
		
		return this.userToDto(user) ;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// 
		
		List<User> users = this.userRepo.findAll();
		// converting users to userDto type
		List<UserDto> userList = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public void deleteUser(Integer userId) {

		// 
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " id ", userId));
		this.userRepo.delete(user);
	}

	// method to convert userdto obj to user entity obj
	public User dtoToUser(UserDto userDto) {

		User user = new User();
	
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserAbout(userDto.getUserAbout());
		
		//using model mapper
		//User user=this.modelMapper.map(userDto, User.class);
		
		return user;

	}

	// method to convert user object tyo dto object
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserEmail(user.getUserEmail());
		userDto.setUserPassword(user.getUserPassword());
		userDto.setUserAbout(user.getUserAbout());
		
		//UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
