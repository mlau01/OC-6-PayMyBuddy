package com.paymybuddy.puddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="bankAccount")
public class BankAccount {
	
	@Id
	@Column(name="iban")
	private String iban;
	
	@Column(name="description")
	private String description;

}
