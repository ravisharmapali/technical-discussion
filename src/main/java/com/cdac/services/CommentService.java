package com.cdac.services;

import com.cdac.entities.Comment;
import com.cdac.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId);
	void deleteComment(Integer commentId);
	
}
