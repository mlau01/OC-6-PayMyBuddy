package com.paymybuddy.puddy.model;

import java.util.Date;
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
@Table(name="credit_card")
public class CreditCard {
	
	@Id
	@Column(name="number")
	private String number;
	
	@Column(name="security_code")
	private int securityCode;
	
	@Column(name="expire")
	private Date expire;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "credit_card_id")
	private Set<Versement> versement;

}
