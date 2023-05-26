package com.cdac.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title", length=100, nullable = false)
	private String postTitle;
	
	@Column(name="post_content", length=10000)
	private String postContent;
	
	@Column(name="post_ImageName")
	private String postImageName;
	
	@Column(name="post_date")
	private Date postAddedDate;
	
	//many post can be mapped with single category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	//one user can do many posts
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	// while impl Comment api
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL)
	private Set<Comment> comment=new HashSet<>();

}
 