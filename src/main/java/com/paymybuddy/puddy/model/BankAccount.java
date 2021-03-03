package com.paymybuddy.puddy.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="bank_account")
public class BankAccount {
	
	@Id
	@Column(name="iban")
	private String iban;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_account_iban")
	private Set<Versement> versement;

}
