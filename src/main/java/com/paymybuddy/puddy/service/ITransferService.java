package com.paymybuddy.puddy.service;

import org.springframework.data.domain.Page;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidArgumentException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.Transfer;

public interface ITransferService {
	/**
	 * Should realize a balance transfer between a transmitter and a recipient
	 * @param transmitterMail
	 * @param recipientMail
	 * @param amount
	 * @param currency
	 * @param description
	 * @return Should return Transfer object created if successful
	 * @throws NotEnoughCreditException Should be thrown if transmitter as not enough balance to make the transfer
	 * @throws InvalidArgumentException Should be thrown if an argument is invalid
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public Transfer doTransfer(String transmitterMail, String recipientMail, String amount, CURRENCY currency, String description) 
			throws NotEnoughCreditException, InvalidArgumentException;
	
	/**
	 * Should give a pagination result of all transfer realized by the user
	 * @param mail user mail
	 * @param page number of the page should be return
	 * @return Page<Transfer> a pagination result
	 * @author Mathias Lauer
	 * 17 mars 2021
	 */
	public Page<Transfer> getTransferOfUser(String mail, int page);
}
