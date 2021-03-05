package com.paymybuddy.puddy.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidAmountException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.TransferRepository;

@Service
public class TransferServiceImpl implements ITransferService {
	
	private UserServiceImpl userService;
	private TransferRepository transferRepo;
	private static final double tax = 0.05;
	
	@Autowired
	public TransferServiceImpl(UserServiceImpl p_userService, TransferRepository p_transferRepository) {
		userService = p_userService;
		transferRepo = p_transferRepository;
	}
	
	/**
	 * Transactional implementation doing a balance transfer between a transmitter and a recipient user
	 * Create a Transfer object representing the transfer.
	 * @param transmitterMail Mail of the transmitter user
	 * @param recipientMail Mail of the recipient user
	 * @param amount Amount of money to transfer
	 * @param currency Currency used based on CURRENCY enumeration
	 * @param description A description of the transfer
	 * @return Transfer object created
	 * @throws {@link InvalidAmountException}
	 * @throws {@link NotEnoughCreditException}
	 * @author Mathias Lauer
	 * 5 mars 2021
	 */
	@Transactional
	public Transfer doTransfer(String transmitterMail, String recipientMail, double amount, CURRENCY currency, String description) 
			throws NotEnoughCreditException, InvalidAmountException {
		
		if(amount <= 0) {
			throw new InvalidAmountException("Amount cannot be 0 or minus");
		}
		
		User transmitterUser = userService.getUserByMail(transmitterMail);
		User recipientUser = userService.getUserByMail(recipientMail);
		
		//Calculate taxed amount and check if transmitter has credit to continue
		double taxedAmount = amount + (amount * 0.05);
		double transmitterBalance = transmitterUser.getBalance();
		if(taxedAmount > transmitterBalance) {
			throw new NotEnoughCreditException("insufficient credit to do this transfer");
		}
		
		userService.debit(transmitterUser, taxedAmount);
		userService.credit(recipientUser, amount);
		
		Transfer transfer = new Transfer();

		transfer.setSource(transmitterUser);
		transfer.setRecipient(recipientUser);
		transfer.setCurrency(currency);
		transfer.setAmount(amount);
		transfer.setDate(Date.from(Instant.now()));
		transfer.setTax(tax);
		transfer.setDescription(description);

		return transferRepo.save(transfer);
	}

}
