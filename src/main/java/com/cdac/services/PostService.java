package com.cdac.services;

import java.util.List;

import com.cdac.payloads.PostDto;
import com.cdac.payloads.PostResponse;
/**
 * 
 * @author Ravi Sharma 
 * 
 *
 */
public interface PostService {

	// to make a post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// to update a post
	PostDto updatePost(PostDto postDto, Integer postId);

	// to delete a post
	void deletePost(Integer postId);

	// to get all the posts
	// List<PostDto> getAllPost();
	// task: re-implement using pagination:

	// List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	// we will change the return type
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);

	// to get single post
	PostDto getPostById(Integer postId);

	// to get all the post by category
	List<PostDto> getPostsByCategory(Integer catId);

	// to get all the posts by user
	
	List<PostDto> getPostsByUser(Integer userId);
//	List<PostDto> getPostsByUser(Integer userId, Integer pageNumber,Integer pageSize);

	// search posts
	List<PostDto> searchPosts(String keyword);
}
