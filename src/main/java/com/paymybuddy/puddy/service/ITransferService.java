package com.paymybuddy.puddy.service;

import org.springframework.data.domain.Page;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidArgumentException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;

public interface ITransferService {
	public Transfer doTransfer(String sourceMail, String recipientMail, String amount, CURRENCY currency, String description) 
			throws NotEnoughCreditException, InvalidArgumentException;
	
	public Page<Transfer> getTransferOfUser(String mail, int page);
}
