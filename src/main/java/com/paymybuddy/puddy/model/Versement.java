package com.paymybuddy.puddy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paymybuddy.puddy.enums.CURRENCY;

import lombok.Data;

@Entity
@Data
@Table(name="versement")
public class Versement {
	
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "credit_card_number")
	private String creditCardNumber;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private CURRENCY currency;
	
	@Column(name="description")
	private String description;

}
