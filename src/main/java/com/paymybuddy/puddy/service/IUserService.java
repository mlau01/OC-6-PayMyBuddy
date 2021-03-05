package com.paymybuddy.puddy.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.paymybuddy.puddy.model.User;

public interface IUserService {
	public UserDetails loadUserByUsername(String mail);
	public User getUserByMail(String mail);
	public void credit(User user, double amount);
	public void debit(User user, double amount);
}
