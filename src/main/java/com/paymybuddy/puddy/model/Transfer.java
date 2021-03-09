package com.paymybuddy.puddy.model;

import java.util.Date;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paymybuddy.puddy.enums.CURRENCY;

import lombok.Data;

@Entity
@Data
@Table(name="transfer")
public class Transfer {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private CURRENCY currency;
	
	@Column(name="tax")
	private Double tax;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne()
	@JoinColumn(name = "transmitter_user_id")
	private User transmitter;
	
	@ManyToOne()
	@JoinColumn(name = "recipient_user_id")
	private User recipient;

}
