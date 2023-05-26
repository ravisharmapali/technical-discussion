package com.cdac.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Ravi Sharma
 * this class we are using to send some informations along with response to client.
 */
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;

	private Boolean isLastPage;
	
}
