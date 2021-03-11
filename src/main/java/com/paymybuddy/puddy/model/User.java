package com.paymybuddy.puddy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.paymybuddy.puddy.enums.CURRENCY;

import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User implements Serializable, UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "balance")
	private Double balance;
	
	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private CURRENCY currency;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "contact",
				joinColumns = {@JoinColumn(name="user_id")},
				inverseJoinColumns = {@JoinColumn(name="contact_user_id")})
	private Set<User> contacts;
	
	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_user_id")
	private Set<BankAccount> bankAccounts;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "transmitter_user_id")
	private Set<Transfer> transfers;
	*/
	
	public String getUsername() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
