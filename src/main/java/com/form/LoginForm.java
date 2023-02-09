package com.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.validation.First;
import com.validation.Second;
import com.validation.Third;

import lombok.Data;

@Data
public class LoginForm {
	
	@NotBlank(groups = First.class)
	@Email(groups = Second.class)
	private String email;

	@NotBlank(groups = First.class)
	@Length(min = 4, max = 10, groups = Second.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = Third.class)
	private String password;
	
}