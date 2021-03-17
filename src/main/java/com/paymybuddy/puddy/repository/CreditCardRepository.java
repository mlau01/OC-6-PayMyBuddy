package com.paymybuddy.puddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.puddy.model.CreditCard;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, String> {

}
