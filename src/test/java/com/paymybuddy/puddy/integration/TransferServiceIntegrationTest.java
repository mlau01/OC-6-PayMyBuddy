package com.paymybuddy.puddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidAmountException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.IUserService;

@SpringBootTest
public class TransferServiceIntegrationTest {
	
	@Autowired
	private ITransferService transferService;
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void doTransferTest_shouldCreateNewTranferAndUpdateUserBalances() throws NotEnoughCreditException, InvalidAmountException {
		final double amount = 12;
		User transmitter = userService.getUserByMail("matt.lau@gmail.com");
		User recipient = userService.getUserByMail("yann.lau@gmail.com");
		userService.credit(transmitter, 15);
		double transmitterBalanceBfTransfer = transmitter.getBalance();
		double recipientBalanceBfTransfer = recipient.getBalance();
		
		transferService.doTransfer("matt.lau@gmail.com", "yann.lau@gmail.com", amount, CURRENCY.EUR, "test");
		
		assertEquals(transmitterBalanceBfTransfer - (amount + (amount * 0.05)), userService.getUserByMail("matt.lau@gmail.com").getBalance());
		assertEquals(recipientBalanceBfTransfer + amount, userService.getUserByMail("yann.lau@gmail.com").getBalance());
	}
	
	@Test
	public void getUserTransfers_shouldReturnSetOfTransfer() {
		Iterator<Transfer> transfersIterator = transferService.getTransferOfUser("matt.lau@gmail.com");
		
		assertEquals("test", transfersIterator.next().getDescription());
	}
}
