package com.paymybuddy.puddy.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidAmountException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.IUserService;

@Controller
public class Login {
	
	private static Logger log = LoggerFactory.getLogger(Login.class);
	
	private IUserService userService;
	private ITransferService transferService;
	
	@Autowired
	public Login(IUserService p_userService, ITransferService p_transferService) {
		userService = p_userService;
		transferService = p_transferService;
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value="/")
	public String root(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());
		return "home";
	}
	
	@GetMapping(value="/home")
	public String home(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());
		return "home";
	}
	
	@GetMapping(value="/transfer")
	public String transfer(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
		User user = userService.getUserByMail(principal.getName());
		Page<Transfer> pages = transferService.getTransferOfUser(principal.getName(), page);
		model.addAttribute("contacts", user.getContacts());
		model.addAttribute("transfers", pages);
		model.addAttribute("transferTotalPages", pages.getTotalPages());
		return "transfer";
	}
	
	@PostMapping(value="/transfer")
	public String doTransfer(Principal principal,
			@RequestParam("connections") String connections,
			@RequestParam("amount") String amount,
			@RequestParam("description") String description) {
		log.info("POST Request on /transfer with params: connections: {}, amount: {}, description: {}", connections, amount, description);
		
		try {
			transferService.doTransfer(principal.getName(), connections, Double.parseDouble(amount), CURRENCY.EUR, description);
		} catch (NumberFormatException | NotEnoughCreditException | InvalidAmountException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/transfer";
	}

}
