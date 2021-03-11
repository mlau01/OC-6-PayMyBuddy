package com.paymybuddy.puddy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.service.UserServiceImpl;

@Controller
public class Login {
	
	private UserServiceImpl userService;
	
	@Autowired
	public Login(UserServiceImpl p_userService) {
		userService = p_userService;
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value="/home")
	public String home(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());
		return "home";
	}
	
	@GetMapping(value="/transfer")
	public String transfer(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
		User user = userService.getUserByMail(principal.getName());
		model.addAttribute("contacts", user.getContacts());
		//ymodel.addAttribute("transfers", userService.getTransfers(mail, page));
		return "transfer";
	}

}
