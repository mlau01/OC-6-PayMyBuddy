package com.paymybuddy.puddy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {
	
	@GetMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value="/home")
	public String home(Principal principal) {
		System.out.println(principal.getName());
		return "home";
	}

}
