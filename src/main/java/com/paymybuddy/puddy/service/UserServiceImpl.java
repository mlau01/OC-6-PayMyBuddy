package com.paymybuddy.puddy.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.AlreadyExistContactException;
import com.paymybuddy.puddy.exceptions.EmailAlreadyExistsException;
import com.paymybuddy.puddy.exceptions.PasswordNotMatchException;
import com.paymybuddy.puddy.form.UserForm;
import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.ContactRepository;
import com.paymybuddy.puddy.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	
	private UserRepository userRepository;
	private ContactRepository contactRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository p_userRepo, ContactRepository p_contactRepository) {
		userRepository = p_userRepo;
		contactRepo = p_contactRepository;
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
	 * Get the contact list of an user
	 * @param mail of the user
	 * @return Iterator<User> Iterator over the user contact list
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	@Transactional(readOnly = true)
	public Iterator<User> getUserContactsByMail(String mail){
		User user = getUserByMail(mail);
		
		return user.getContacts().iterator();
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
	
	/**
	 * Add a contact by his mail address
	 * @param userMail of the user
	 * @param contactMail of the contact
	 * @author Mathias Lauer
	 * 7 mars 2021
	 * @throws AlreadyExistContactException 
	 */
	public Contact addContact(String userMail, String contactMail) throws AlreadyExistContactException {
		User user = getUserByMail(userMail);
		User contact = getUserByMail(contactMail);
		
		if(contactRepo.existsByUserAndContact(user, contact)) {
			throw new AlreadyExistContactException();
		}
		
		Contact newContact = new Contact();
		newContact.setUser(user);
		newContact.setContact(contact);
		
		return contactRepo.save(newContact);	
	}

	/**
	 * Persist an user in data base
	 * @param userForm from containing user datas
	 * @return User object persisted if successful
	 * @throws PasswordNotMatchException if password confirmation failed
	 * @throws EmailAlreadyExistsException if email already exists
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public User addNewUser(UserForm userForm) throws PasswordNotMatchException, EmailAlreadyExistsException {
		checkPassword(userForm);
		checkEmail(userForm);
		
		User newUser = new User();
		newUser.setCurrency(CURRENCY.EUR);
		newUser.setEmail(userForm.getEmail());
		newUser.setPassword(userForm.getPassword());
		newUser.setFirstName(userForm.getFirstName());
		newUser.setLastName(userForm.getLastName());
		newUser.setBalance(0.);
		
		return userRepository.save(newUser);
		
	}

	/**
	 * Update an user in data base
	 * @param userForm from containing user datas
	 * @param user to update 
	 * @return User object persisted if successful
	 * @throws PasswordNotMatchException if password confirmation failed
	 * @throws EmailAlreadyExistsException if email already exists
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	@Override
	public User editUser(UserForm userForm, User user) throws PasswordNotMatchException, EmailAlreadyExistsException {
		checkPassword(userForm);
		if( ! user.getEmail().equals(userForm.getEmail())) {
			checkEmail(userForm);
			
			user.setEmail(userForm.getEmail());
		}
	
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setPassword(userForm.getPassword());
		
		return userRepository.save(user);
	}
	
	private void checkPassword(UserForm userForm) throws PasswordNotMatchException {
		if( ! userForm.getPassword().equals(userForm.getPassword_confirm())) {
			throw new PasswordNotMatchException("La confirmation du mot de passe doit correspondre");
		}
	}
	
	private void checkEmail(UserForm userForm) throws EmailAlreadyExistsException {
		if(userRepository.existsByEmail(userForm.getEmail())) {
			throw new EmailAlreadyExistsException("Cette email existe d??j??");
		}
	}

}
