package com.paymybuddy.puddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paymybuddy.puddy.enums.BILLING_STATUS;
import com.paymybuddy.puddy.enums.CURRENCY;

import lombok.Data;

@Entity
@Data
@Table(name="billing")
public class Billing {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private CURRENCY currency;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private BILLING_STATUS status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id")
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transfer_id")
	private Transfer transfer;

}
