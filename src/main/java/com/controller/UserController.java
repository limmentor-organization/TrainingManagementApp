package com.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.UserDto;
import com.security.UserDetailsImpl;
import com.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public String getAdmin(Model model, @AuthenticationPrincipal UserDetailsImpl user) {
		List<UserDto> users = userService.getUserList();
		model.addAttribute("users", users);
		model.addAttribute("screen", "user/users :: users_contents");
		return "layout";
	}

}