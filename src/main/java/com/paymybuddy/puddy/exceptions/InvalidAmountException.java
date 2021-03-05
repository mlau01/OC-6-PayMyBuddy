package com.paymybuddy.puddy.exceptions;

/**
 * Exception thrown if an invalid amount is used somewhere
 * @author Mathias Lauer
 * 5 mars 2021
 */
public class InvalidAmountException extends Exception {

	public InvalidAmountException(String string) {
		super(string);
	}

}
