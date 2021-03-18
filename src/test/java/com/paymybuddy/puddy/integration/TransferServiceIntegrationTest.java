package com.paymybuddy.puddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidArgumentException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.BillingRepository;
import com.paymybuddy.puddy.repository.TransferRepository;
import com.paymybuddy.puddy.repository.UserRepository;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.IUserService;

@SpringBootTest
public class TransferServiceIntegrationTest {
	
	@Autowired
	private ITransferService transferService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private TransferRepository transferRepo;
	
	@Autowired
	private BillingRepository billingRepo;
	
	@BeforeAll
	public static void setUp(@Autowired UserRepository userRepository) {
		User transmitter = new User();
		transmitter.setFirstName("Tran");
		transmitter.setLastName("Mitter");
		transmitter.setPassword("test");
		transmitter.setCurrency(CURRENCY.EUR);
		transmitter.setBalance(20.);
		transmitter.setEmail("transmitter@mail.com");
		userRepository.save(transmitter);
		
		
		User recipient = new User();
		recipient.setFirstName("Reci");
		recipient.setLastName("Pient");
		recipient.setPassword("test");
		recipient.setCurrency(CURRENCY.EUR);
		recipient.setBalance(0.);
		recipient.setEmail("recipient@mail.com");
		userRepository.save(recipient);
	}
	
	@AfterAll
	public static void clear(@Autowired UserRepository userRepository) {
		userRepository.deleteAll();
	}
	
	@Test
	public void doTransferTest_shouldCreateNewTranferAndUpdateUserBalances() throws NotEnoughCreditException, InvalidArgumentException {
		final double amount = 12;
		User transmitter = userService.getUserByMail("transmitter@mail.com");
		User recipient = userService.getUserByMail("recipient@mail.com");
		double transmitterBalanceBfTransfer = transmitter.getBalance();
		double recipientBalanceBfTransfer = recipient.getBalance();
		
		Transfer transfer = transferService.doTransfer("transmitter@mail.com", "recipient@mail.com", String.valueOf(amount), CURRENCY.EUR, "test");
		
		assertEquals(transmitterBalanceBfTransfer - (amount + (amount * 0.05)), userService.getUserByMail("transmitter@mail.com").getBalance());
		assertEquals(recipientBalanceBfTransfer + amount, userService.getUserByMail("recipient@mail.com").getBalance());
		assertNotNull(transfer);
		
		billingRepo.deleteAll();
		transferRepo.deleteAll();
	}
}
