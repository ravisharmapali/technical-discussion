package com.cdac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
	
}
