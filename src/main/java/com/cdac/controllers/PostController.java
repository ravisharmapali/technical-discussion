package com.cdac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.config.AppConstants;
import com.cdac.payloads.ApiResponse;
import com.cdac.payloads.PostDto;
import com.cdac.payloads.PostResponse;
import com.cdac.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		System.out.println("post controller create method");
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		System.out.println("inside getPost");
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

//	@GetMapping("/user/{userId}/posts")
//	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
//		
//		List<PostDto> postsByUser = postService.getPostsByUser(userId);
//		return new ResponseEntity<List<PostDto>>(postsByUser,HttpStatus.OK);
//	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

		List<PostDto> postsByUser = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {

		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
		System.out.println(postsByCategory);
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
	}

//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDto>> getAllPosts(){
//		 List<PostDto> allPost = this.postService.getAllPost();
//		 return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
//	}
//	

	@GetMapping("/posts")
	// public ResponseEntity<List<PostDto>> getAllPosts(
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
//		 List<PostDto> allPost = this.postService.getAllPost(pageNumber,pageSize);
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy);
//		 return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public ApiResponse deleteById(@PathVariable Integer postId) {

		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted successfully", true);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// this method will help to search posts ( keywords will be received through
	// url)
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDto> searchPosts = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
	}
}
