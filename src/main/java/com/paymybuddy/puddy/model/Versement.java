package com.paymybuddy.puddy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paymybuddy.puddy.CURRENCY;

import lombok.Data;

@Entity
@Data
@Table(name="versement")
public class Versement {
	
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="currency")
	private CURRENCY currency;
	
	@Column(name="description")
	private String description;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_user_id")
	private User recipient;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="bank_account_iban")
	private BankAccount bankAccount;


}
