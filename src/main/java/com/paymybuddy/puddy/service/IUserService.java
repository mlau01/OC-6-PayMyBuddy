package com.paymybuddy.puddy.service;

import java.util.Iterator;

import com.paymybuddy.puddy.exceptions.AlreadyExistContactException;
import com.paymybuddy.puddy.exceptions.EmailAlreadyExistsException;
import com.paymybuddy.puddy.exceptions.PasswordNotMatchException;
import com.paymybuddy.puddy.form.UserForm;
import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;

public interface IUserService {
	
	/**
	 * Should give the an User object
	 * @param mail of the User
	 * @return User object
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public User getUserByMail(String mail);
	
	/**
	 * Should credit the user
	 * @param user to credit
	 * @param amount amount to credit
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public User credit(User user, double amount);
	
	/**
	 * Should debit the user
	 * @param user to debit
	 * @param amount amount to debit
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public User debit(User user, double amount);
	
	/**
	 * Should persist a new user in db
	 * @param userForm an UserForm representing input entered
	 * @return User object persisted
	 * @throws PasswordNotMatchException Should be thrown if password check failed
	 * @throws EmailAlreadyExistsException Should be thrown if email already exists
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public User addNewUser(UserForm userForm) throws PasswordNotMatchException, EmailAlreadyExistsException;
	
	/**
	 * Should update user profile
	 * @param userForm form with the edited data
	 * @param user to update
	 * @return User object updated
	 * @throws PasswordNotMatchException Should be thrown if password check failed
	 * @throws EmailAlreadyExistsException Should be thrown if email already exists
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public User editUser(UserForm userForm, User user) throws PasswordNotMatchException, EmailAlreadyExistsException;
	
	/**
	 * Should add a new contact in user contact list
	 * @param userMail of the current user
	 * @param contactMail of the contact will be add
	 * @return Contact object created
	 * @throws AlreadyExistContactException Should be thrown if the contact is already in user contact list
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public Contact addContact(String userMail, String contactMail) throws AlreadyExistContactException;
	
	/**
	 * Should return an iterator over the contact list of an user
	 * @param mail of the user
	 * @return Iterator<User> Iterator over contact list
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public Iterator<User> getUserContactsByMail(String mail);
}
