package com.paymybuddy.puddy.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository p_userRepo) {
		userRepository = p_userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException{
		Objects.requireNonNull(mail);
		User user = userRepository.findAllByEmail(mail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return user;
	}

}
