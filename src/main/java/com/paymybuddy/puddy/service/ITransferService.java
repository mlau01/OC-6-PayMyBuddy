package com.paymybuddy.puddy.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidAmountException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;

public interface ITransferService {
	public Transfer doTransfer(String sourceMail, String recipientMail, double amount, CURRENCY currency, String description) 
			throws NotEnoughCreditException, InvalidAmountException;
	
	public Page<Transfer> getTransferOfUser(String mail, int page);
}
