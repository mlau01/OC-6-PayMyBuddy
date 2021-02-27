package com.paymybuddy.puddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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
		Optional<User> users = userRepository.findById(1);
		
		User firstUser = users.get();
		
		assertEquals("Matt", firstUser.getFirstName());
		assertEquals("Lau", firstUser.getLastName());
		assertEquals("test", firstUser.getPassword());
		assertEquals("matt.lau@gmail.com", firstUser.getEmail());
		assertEquals(0, firstUser.getBalance());
		assertEquals(CURRENCY.EUR, firstUser.getCurrency());
		assertEquals("yann.lau@gmail.com", firstUser.getContacts().iterator().next().getEmail());
		//INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Matt", "Lau", "test", "matt.lau@gmail.com", 0, "EUR");
	}


}
