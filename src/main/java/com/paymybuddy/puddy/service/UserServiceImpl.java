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
public class UserServiceImpl implements UserDetailsService {

	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository p_userRepo) {
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
		
		return getUserByMail(mail);
	}
	
	/**
	 * Get an user by his mail
	 * @param mail
	 * @return User object
	 * @author Mathias Lauer
	 * 5 mars 2021
	 */
	public User getUserByMail(String mail) {
		User user = userRepository.findAllByEmail(mail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return user;
	}
	
	/**
	 * Credit the balance of an user
	 * @param user User to credit
	 * @param amount Amount to credit
	 * @return User object persisted
	 * @author Mathias Lauer
	 * 5 mars 2021
	 */
	public User credit(User user, double amount) {
		user.setBalance(user.getBalance() + amount);
		return userRepository.save(user);
	}
	
	/**
	 * Debit the balance of an user
	 * @param user User to debit
	 * @param amount Amount to debit
	 * @return User object persisted
	 * @author Mathias Lauer
	 * 5 mars 2021
	 */
	public User debit(User user, double amount) {
		user.setBalance(user.getBalance() - amount);
		return userRepository.save(user);
	}

}
