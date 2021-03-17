package com.paymybuddy.puddy.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.UserRepository;

/**
 * Used by Spring Security for login
 * @author Mathias Lauer
 * 17 mars 2021
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository p_userRepo) {
		userRepository = p_userRepo;
	}
	/**
	 * Used by Spring Security for login
	 * Encapsulation of an User retrieved by mail
	 * @param mail Mail
	 * @return UserDetails object encapsulate the User
	 * @author Mathias Lauer
	 * 5 mars 2021
	 */
	@Override
	public UserDetails loadUserByUsername(String mail){
		Objects.requireNonNull(mail);
		User user = userRepository.findAllByEmail(mail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return user;
	}
	
	

}
