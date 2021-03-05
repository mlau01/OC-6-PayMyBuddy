package com.paymybuddy.puddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.puddy.model.BankAccount;
import com.paymybuddy.puddy.repository.BankAccountRepository;

@SpringBootTest
public class BankAccountRepositoryTest {
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	@Test
	public void testGetFirstEntry_shouldReturnFirstEntry() {
		Optional<BankAccount> firstBankAccount = bankAccountRepo.findById("FR00212332043JKRE20");
		
		BankAccount bankAccount = firstBankAccount.get();
		
		assertNotNull(bankAccount);
		assertEquals("CCP", bankAccount.getDescription());
		
	}

}
