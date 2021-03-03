package com.paymybuddy.puddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.puddy.model.BankAccount;

@Repository
public interface BankAccountRepository  extends CrudRepository<BankAccount, String> {

}
