package com.paymybuddy.puddy.service;

import java.util.Iterator;

import com.paymybuddy.puddy.exceptions.AlreadyExistContactException;
import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;

public interface IUserService {
	public User getUserByMail(String mail);
	public User credit(User user, double amount);
	public User debit(User user, double amount);
	public void addNewUser(String string, String string2, String string3, String string4);
	public Contact addContact(String userMail, String contactMail) throws AlreadyExistContactException;
	public Iterator<User> getUserContactsByMail(String mail);
}
