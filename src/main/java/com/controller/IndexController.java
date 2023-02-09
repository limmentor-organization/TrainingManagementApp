package com.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.UserDetailsImpl;

@Controller
public class IndexController {

	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
		model.addAttribute("screen", "index/home :: home_contents");
		return "layout";
	}

}
