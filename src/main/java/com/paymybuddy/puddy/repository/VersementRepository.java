package com.paymybuddy.puddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.puddy.model.Versement;

@Repository
public interface VersementRepository extends CrudRepository<Versement, Integer>{

}
