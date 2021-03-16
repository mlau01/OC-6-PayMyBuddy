package com.paymybuddy.puddy.service;

import java.util.Iterator;

import com.paymybuddy.puddy.exceptions.AlreadyExistContactException;
import com.paymybuddy.puddy.exceptions.EmailAlreadyExistsException;
import com.paymybuddy.puddy.exceptions.PasswordNotMatchException;
import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.model.UserForm;

public interface IUserService {
	public User getUserByMail(String mail);
	public User credit(User user, double amount);
	public User debit(User user, double amount);
	public User addNewUser(UserForm userForm) throws PasswordNotMatchException, EmailAlreadyExistsException;
	public Contact addContact(String userMail, String contactMail) throws AlreadyExistContactException;
	public Iterator<User> getUserContactsByMail(String mail);
}
