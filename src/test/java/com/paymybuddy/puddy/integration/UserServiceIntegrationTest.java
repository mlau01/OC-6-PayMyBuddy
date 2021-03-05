package com.paymybuddy.puddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.service.UserServiceImpl;

@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	UserServiceImpl userService;
	
	@Test
	public void updateBalanceTest_shouldUpdateCorrectlyUserBalance() {
		User user = userService.getUserByMail("matt.lau@gmail.com");
		double UserBalanceBfCredit = user.getBalance();
		userService.credit(user, 304);
		
		assertEquals(UserBalanceBfCredit + 304, user.getBalance());
	}
	
}
