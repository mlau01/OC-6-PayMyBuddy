package com.paymybuddy.puddy.exceptions;

/**
 * Exception thrown if a transmitter does not have enough credit to perform an operation
 * @author Mathias Lauer
 * 5 mars 2021
 */
public class NotEnoughCreditException extends Exception {
	
	public NotEnoughCreditException() {
		super();
	}
	
	public NotEnoughCreditException(String s) {
		super(s);
	}

}
