package com.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dto.UserDto;
import com.form.LoginForm;
import com.form.UserForm;
import com.service.UserService;
import com.validation.All;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {

	private final UserService userService;

	@GetMapping("/login")
	public String login(@ModelAttribute LoginForm loginForm) {
		return "auth/login";
	}

	@GetMapping("/signup")
	public String signup(@ModelAttribute UserForm userForm, Model model) {
		return "auth/signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute @Validated(All.class) UserForm userForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return signup(userForm, model);
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userForm, userDto);
		try {
			userService.signup(userDto);
		} catch (DuplicateKeyException e) {
			model.addAttribute("msg", e.getMessage());
			return signup(userForm, model);
		} catch (Exception e) {
			model.addAttribute("msg", "signup error");
			return "error";
		}
		return "redirect:/login";
	}
}
