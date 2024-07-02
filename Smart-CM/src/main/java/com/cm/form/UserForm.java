package com.cm.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
	// @NotBlank(message = "Username is Required")
	@Size(min = 3,message = "should be have length bigger than 3")
	private String name;

	@Email(message = "Invalid Email Address")
	@NotBlank(message = "can't be blank")
	private String email;
	// @NotBlank(message="can't blank")
	@Size(min = 6,message = "Password should be bigger than 6 character")
	private String password;
	@NotBlank
	private String about;
	@Size(min = 10, max = 12, message = "should be greater than and less than 12")
	private String phoneNumber;
	
}
