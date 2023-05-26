package com.cdac.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Category;
import com.cdac.entities.Post;
import com.cdac.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	//to get all posts by a user
	List<Post> findByUser(User user);
	
	//to find all post of a category
	List<Post> findByCategory(Category category);

	// to apply searching 
	List<Post> findByPostTitleContaining(String postTitle);
}
