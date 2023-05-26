package com.cdac.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cdac.entities.Category;
import com.cdac.entities.Post;
import com.cdac.entities.User;
import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.payloads.PostDto;
import com.cdac.payloads.PostResponse;
import com.cdac.repositories.CategoryRepo;
import com.cdac.repositories.PostRepo;
import com.cdac.repositories.UserRepo;
import com.cdac.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = modelMapper.map(postDto, Post.class);
		// setting remaining properties

		post.setPostImageName("default.png");
		post.setPostAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		// saving
		Post savedPost = this.postRepo.save(post);

		return modelMapper.map(savedPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImageName(postDto.getPostImageName());

		Post savedPost = this.postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		//

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		// delete this post
		this.postRepo.delete(post);
	}

	@Override
//	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {

	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		// //
//		List<Post> allPost = this.postRepo.findAll();
//		
//		//now convert to PostDto list
//		List<PostDto> collect = allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
//		return collect;
//		

//		int pageSize=5;
//		int pageNumber=1;

//		Pageable p = PageRequest.of(pageNumber, pageSize);

		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)); // applied sorting
		// Sort.by(sortBy).ascending or decesding can be used
		// it will return only limited posts--
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();

		// now convert to PostDto list
		List<PostDto> collect = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		// creating PostResponse
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(pagePost.getNumber()); // getNumber
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setIsLastPage(pagePost.isLast());

		return postResponse;
//		

	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		System.out.println("inside serice method ");
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDto> list = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// fetch user which id is supplied
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		// now get all post by that user

		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> list = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return list;
	}

	/*
	 * @Override public List<PostDto> getPostsByUser(Integer userId, Integer
	 * pageNumber, Integer pageSize) {
	 * 
	 * Pageable page= PageRequest.of(pageNumber, pageSize);
	 * 
	 * 
	 * Page<Post> findAll = postRepo.findAll(page);
	 * 
	 * List<Post> content = findAll.getContent();
	 * 
	 * List<PostDto> postDtos = content.stream().map((post)->modelMapper.map(post,
	 * PostDto.class)).collect(Collectors.toList());
	 * 
	 * 
	 * // fetch user which id is supplied User user = this.userRepo.findById(userId)
	 * .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
	 * // now get all post by that user
	 * 
	 * List<Post> posts = this.postRepo.findByUser(user);
	 * 
	 * 
	 * List<PostDto> list = posts.stream().map((post) -> this.modelMapper.map(post,
	 * PostDto.class)) .collect(Collectors.toList());
	 * 
	 * return list; }
	 */
	@Override
	public List<PostDto> searchPosts(String keyword) {
		//
		List<Post> postDtos = this.postRepo.findByPostTitleContaining(keyword);
		
		return postDtos.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	}

}
