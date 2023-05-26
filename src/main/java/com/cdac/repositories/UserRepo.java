package com.cdac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
}
