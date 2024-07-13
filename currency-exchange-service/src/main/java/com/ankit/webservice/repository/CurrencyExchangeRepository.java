package com.ankit.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.webservice.entity.CurrencyExcahnge;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExcahnge, Long>{
	CurrencyExcahnge findByFromAndTo(String from,String to);
}
