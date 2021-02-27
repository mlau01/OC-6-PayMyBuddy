package com.paymybuddy.puddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testFindFirstUser_shouldReturnTestUser() {
		Iterable<User> users = userRepository.findAll();
		Iterator<User> iterator = users.iterator();
		
		User firstUser = iterator.next();
		
		assertEquals("Matt", firstUser.getFirstName());
	}


}
