package com.uday.pointscalculator.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.uday.pointscalculator.entity.CustomerTransaction;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long>{
	
//	@Query(nativeQuery = true, value = "select ID ,CUSTOMER_ID,AMOUNT,TRANSACTION_DATE from TRANSACTION where CUSTOMER_ID=:customerId and (parsedatetime(TRANSACTION_DATE, 'yyyy-MM-dd hh:mm:ss.SS') between :from and :to);")
//	Optional<List<CustomerTransaction>> findTransactions(Long customerId, String from,String to);
	
	Optional<List<CustomerTransaction>> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from,Timestamp to);

}
