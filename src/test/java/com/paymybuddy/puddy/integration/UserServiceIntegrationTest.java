package com.paymybuddy.puddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paymybuddy.puddy.exceptions.AlreadyExistContactException;
import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.ContactRepository;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.IUserService;

@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ITransferService transferService;
	
	@Test
	public void updateBalanceTest_shouldUpdateCorrectlyUserBalance() {
		User user = userService.getUserByMail("matt.lau@gmail.com");
		double UserBalanceBfCredit = user.getBalance();
		userService.credit(user, 304);
		
		assertEquals(UserBalanceBfCredit + 304, user.getBalance());
	}
	
	@Test
	public void addUnknownContactTest_shouldReturnException() {
		assertThrows(UsernameNotFoundException.class, () -> userService.addContact("matt.lau@gmail.com", "unknown"));
	}
	
	@Test
	public void addAlreadyExistContactTest_shouldReturnException() {
		assertThrows(AlreadyExistContactException.class, () -> userService.addContact("matt.lau@gmail.com", "yann.lau@gmail.com"));
	}
	
	@Test
	public void addContactTest_shouldReturnContactObjectCreated() throws AlreadyExistContactException {
		Contact contact = userService.addContact("matt.lau@gmail.com", "jean.joubler@gmail.com");
		contactRepo.delete(contact);
		
		assertNotNull(contact);
	}
	
	@Test
	public void getUserContacts_shouldReturnSetOfUser() {
		Iterator<User> contactsIterator = userService.getUserContactsByMail("matt.lau@gmail.com");

		assertTrue(contactsIterator.hasNext());
	}
	
	@Test
	public void addNewUser_shouldReturnUserCreated() {
		//userService.addNewUser();
	}
	
}
