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
public class UserForm {

	private int id;

	@NotBlank(groups = First.class)
	@Length(max = 10, min = 1, groups = Second.class)
	private String name;

	@NotBlank(groups = First.class)
	@Length(max = 10, min = 4, groups = Second.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = Third.class)
	private String password;

	@NotBlank(groups = First.class)
	@Email(groups = Second.class)
	private String email;

}
