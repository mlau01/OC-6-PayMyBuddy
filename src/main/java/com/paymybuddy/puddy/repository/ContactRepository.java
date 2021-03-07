package com.paymybuddy.puddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.puddy.model.Contact;
import com.paymybuddy.puddy.model.User;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

	boolean existsByUserAndContact(User user, User contact);

}
