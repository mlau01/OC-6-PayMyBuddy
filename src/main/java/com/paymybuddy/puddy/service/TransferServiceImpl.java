package com.paymybuddy.puddy.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.puddy.enums.BILLING_STATUS;
import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidArgumentException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Billing;
import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.BillingRepository;
import com.paymybuddy.puddy.repository.TransferRepository;

@Service
public class TransferServiceImpl implements ITransferService {
	
	private UserServiceImpl userService;
	private TransferRepository transferRepo;
	private BillingRepository billingRepo;
	private static final double tax = 0.05;
	
	@Autowired
	public TransferServiceImpl(UserServiceImpl p_userService, TransferRepository p_transferRepository, BillingRepository p_billingRepository) {
		userService = p_userService;
		transferRepo = p_transferRepository;
		billingRepo = p_billingRepository;
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
	 * @throws InvalidArgumentException 
	 */
	@Transactional(readOnly = false)
	public Transfer doTransfer(String transmitterMail, String recipientMail,
								String amount_string, CURRENCY currency, String description) 
			throws NotEnoughCreditException, InvalidArgumentException {
		
		double amount;
		try {
			amount = Double.parseDouble(amount_string);
		} catch (NumberFormatException e) {
			throw new InvalidArgumentException("Amount format error");
		}
		
		if(transmitterMail == null || transmitterMail.isEmpty() ||
				recipientMail == null || recipientMail.isEmpty() ||
				amount <= 0) {
					throw new InvalidArgumentException("One or more argument are not valid");
				}
		
		User transmitterUser = userService.getUserByMail(transmitterMail);

		//Calculate taxed amount and check if transmitter has credit to continue
		double bill = amount * tax;
		double taxedAmount = amount + bill;
		double transmitterBalance = transmitterUser.getBalance();
		if(taxedAmount > transmitterBalance) {
			throw new NotEnoughCreditException("insufficient credit to do this transfer");
		}
		
		User recipientUser = userService.getUserByMail(recipientMail);
		userService.debit(transmitterUser, taxedAmount);
		userService.credit(recipientUser, amount);
		
		Transfer transfer = new Transfer();
		transfer.setTransmitter(transmitterUser);
		transfer.setRecipient(recipientUser);
		transfer.setCurrency(currency);
		transfer.setAmount(amount);
		transfer.setDate(Date.from(Instant.now()));
		transfer.setTax(tax);
		transfer.setDescription(description);
		
		Transfer succeedTransfer = transferRepo.save(transfer);
		
		Billing billing = new Billing();
		billing.setAmount(bill);
		billing.setCurrency(currency);
		billing.setUser(transmitterUser);
		billing.setStatus(BILLING_STATUS.UNPAID);
		billing.setTransfer(succeedTransfer);
		
		billingRepo.save(billing);

		return succeedTransfer;
	}

	/**
	 * Get a pagineable transfers list of an user, each page contain five transfers
	 * @param mail Mail of the user
	 * @param page Page number
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Transfer> getTransferOfUser(String mail, int page) {
		User user = userService.getUserByMail(mail);
		Page<Transfer> pages = transferRepo.findAllByTransmitter(user, PageRequest.of(page, 5, Sort.by("date").descending()));
		return pages;
	}
	
	

}
