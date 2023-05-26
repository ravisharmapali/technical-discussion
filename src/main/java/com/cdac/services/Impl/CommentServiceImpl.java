package com.cdac.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entities.Comment;
import com.cdac.entities.Post;
import com.cdac.entities.User;
import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.payloads.CommentDto;
import com.cdac.repositories.CommentRepo;
import com.cdac.repositories.PostRepo;
import com.cdac.repositories.UserRepo;
import com.cdac.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId) {
		
		//fetching user
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		// firstly fetching post
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		
		Comment comment = modelMapper.map(commentDto, Comment.class);

		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// 
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
		commentRepo.delete(comment);
		

	}

}
