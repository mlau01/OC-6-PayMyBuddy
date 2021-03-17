package com.paymybuddy.puddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.AlreadyExistContactException;
import com.paymybuddy.puddy.exceptions.EmailAlreadyExistsException;
import com.paymybuddy.puddy.exceptions.PasswordNotMatchException;
import com.paymybuddy.puddy.form.UserForm;
import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.ContactRepository;
import com.paymybuddy.puddy.repository.UserRepository;
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
	
	@Autowired
	UserRepository userRepo;
	
	@BeforeAll
	public static void setUp(@Autowired UserRepository userRepository, 
			@Autowired ContactRepository contactRepo) {
		User user1 = new User();
		user1.setFirstName("Tran");
		user1.setLastName("Mitter");
		user1.setPassword("test");
		user1.setCurrency(CURRENCY.EUR);
		user1.setBalance(0.);
		user1.setEmail("user1@mail.com");
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setFirstName("Reci");
		user2.setLastName("Pient");
		user2.setPassword("test");
		user2.setCurrency(CURRENCY.EUR);
		user2.setBalance(0.);
		user2.setEmail("user2@mail.com");
		userRepository.save(user2);
		
		User user3 = new User();
		user3.setFirstName("Albert");
		user3.setLastName("AZERTY");
		user3.setPassword("test");
		user3.setCurrency(CURRENCY.EUR);
		user3.setBalance(0.);
		user3.setEmail("user3@mail.com");
		userRepository.save(user3);
		
		Contact contact = new Contact();
		contact.setUser(user1);
		contact.setContact(user2);
		contactRepo.save(contact);
	}
	
	@AfterAll
	public static void clear(@Autowired UserRepository userRepository, 
			@Autowired ContactRepository contactRepo) {
		userRepository.deleteAll();
		contactRepo.deleteAll();
	}
	
	@Test
	public void creditUserTest_shouldUpdateCorrectlyUserBalance() {
		User user = userService.getUserByMail("user1@mail.com");
		double UserBalanceBfCredit = user.getBalance();
		userService.credit(user, 100);
		
		assertEquals(UserBalanceBfCredit + 100, user.getBalance());
	}
	
	@Test
	public void debitUserTest_shouldUpdateCorrectlyUserBalance() {
		User user = userService.getUserByMail("user1@mail.com");
		double UserBalanceBfCredit = user.getBalance();
		userService.debit(user, 100);
		
		assertEquals(UserBalanceBfCredit - 100, user.getBalance());
	}
	
	@Test
	public void addUnknownContactTest_shouldReturnException() {
		assertThrows(UsernameNotFoundException.class, () -> userService.addContact("user1@mail.com", "unknown"));
	}
	
	@Test
	public void addAlreadyExistContactTest_shouldReturnException() {
		assertThrows(AlreadyExistContactException.class, () -> userService.addContact("user1@mail.com", "user2@mail.com"));
	}
	
	@Test
	public void addContactTest_shouldReturnContactObjectCreated() throws AlreadyExistContactException {
		Contact contact = userService.addContact("user1@mail.com", "user3@mail.com");
		contactRepo.delete(contact);
		
		assertNotNull(contact);
	}
	
	@Test
	public void getUserContacts_shouldReturnSetOfUser() {
		Iterator<User> contactsIterator = userService.getUserContactsByMail("user1@mail.com");

		assertTrue(contactsIterator.hasNext());
	}
	
	@Test
	public void addNewUser_shouldReturnUserCreated() throws PasswordNotMatchException, EmailAlreadyExistsException {
		UserForm userForm = new UserForm();
		userForm.setFirstName("Eric");
		userForm.setLastName("Yoto");
		userForm.setEmail("eric.yoto@gmail.com");
		userForm.setPassword("test");
		userForm.setPassword_confirm("test");
		
		User user = userService.addNewUser(userForm);
		
		assertEquals("eric.yoto@gmail.com", user.getEmail());
		userRepo.delete(user);
	}
	
	@Test
	public void addUserTest_shouldThrowEmailException() {
		UserForm userForm = new UserForm();
		userForm.setFirstName("Matt");
		userForm.setLastName("Lau");
		userForm.setEmail("user1@mail.com");
		userForm.setPassword("test");
		userForm.setPassword_confirm("test");
		
		assertThrows(EmailAlreadyExistsException.class, () -> userService.addNewUser(userForm));
	}
	
	@Test
	public void updateUserTest_shouldReturnUserUpdated() throws PasswordNotMatchException, EmailAlreadyExistsException {
		UserForm userForm = new UserForm();
		userForm.setFirstName("Matt");
		userForm.setLastName("Lau");
		userForm.setEmail("user1@mail.com");
		userForm.setPassword("test");
		userForm.setPassword_confirm("test");
		
		User user1 = userService.getUserByMail("user1@mail.com");
		
		assertNotNull(userService.editUser(userForm, user1));
	}
	
}
