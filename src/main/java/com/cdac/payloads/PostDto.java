package com.cdac.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;			// so that we can get postId in get all post
	 
	@NotBlank
	@Size(min=5,message = "title must be at least 5 char")
	private String postTitle;
	
	@NotBlank
	@Size(min=10, message="content must be min of 10 character")
	private String postContent;
	
	
	private String postImageName;
	
	private Date postAddedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
	// using this while fetching a Post we will get comments also.
	private Set<CommentDto> comments=new HashSet<>();
	
	
}
 