package com.cdac.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// to transfer user data
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int userId;

	@NotEmpty
	@Size(min = 4, message = "User name must be min of 4 character")
	private String userName;

	@Email(message = "Email address is not valid")
	@NotEmpty(message = "Email required")
	private String userEmail;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must be min of 4 character and max of 10 character")
	private String userPassword;

	@NotEmpty
	private String userAbout;
}
