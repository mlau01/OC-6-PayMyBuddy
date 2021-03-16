package com.paymybuddy.puddy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;

@Repository
public interface TransferRepository  extends CrudRepository<Transfer, Integer>{
	
	public Page<Transfer> findAllByTransmitter(User transmitter, Pageable pageable);

}
