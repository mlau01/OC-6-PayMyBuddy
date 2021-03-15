package com.paymybuddy.puddy.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {
	
	@Size(min = 3, max = 50)
	@NotBlank
	private String firstName;
	
	@Size(min = 3, max = 50)
	@NotBlank
	private String lastName;
	
	@Size(min = 3, max = 50)
	@NotBlank
	private String password;
	
	@Size(min = 3, max = 50)
	@NotBlank
	private String password_confirm;
	
	@Email
	@NotBlank
	private String email;
}