package com.paymybuddy.puddy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.puddy.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	Optional<User> findAllByEmail(String mail);

}
