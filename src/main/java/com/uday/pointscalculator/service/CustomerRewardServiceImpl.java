package com.uday.pointscalculator.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uday.pointscalculator.constants.Constants;
import com.uday.pointscalculator.dao.CustomerTransactionRepository;
import com.uday.pointscalculator.dto.CustomerRewardsResponseDto;
import com.uday.pointscalculator.entity.CustomerTransaction;

@Service
public class CustomerRewardServiceImpl implements CustomerRewardService {

	@Autowired
	private CustomerTransactionRepository custTransactionRepository;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Transactional
	@Override
	public CustomerRewardsResponseDto getRewardPointsByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		
		Optional<List<CustomerTransaction>> listOne = getRewardsForLastMonth(customerId);
		Optional<List<CustomerTransaction>> listTwo = getRewardsForSecondLastMonth(customerId);
		Optional<List<CustomerTransaction>> listThree = getRewardsForThirdLastMonth(customerId);
		
		long lastMonthRewards = getRewardsPerMonth(listOne);
		long secondLastMonthRewards = getRewardsPerMonth(listTwo);
		long thirdLastMonthRewards = getRewardsPerMonth(listThree);
		long totalRewards = lastMonthRewards+secondLastMonthRewards+thirdLastMonthRewards;
		
		CustomerRewardsResponseDto response = new CustomerRewardsResponseDto();
		response.setCustomerId(customerId);
		response.setRewardPointsMonthOne(lastMonthRewards);
		response.setRewardPointsMonthTwo(secondLastMonthRewards);
		response.setRewardPointsMonthThree(thirdLastMonthRewards);
		response.setRewardPointsTotal(totalRewards);
		
		return response;
	}
	
	private Optional<List<CustomerTransaction>> getRewardsForLastMonth(Long customerId) {
		Timestamp lastMonthTimeStamp = getTimeStampForProvidedDuration(Constants.daysInMonths);
//		return custTransactionRepository.findTransactions(customerId, Timestamp.from(Instant.now()).toString(), lastMonthTimeStamp.toString());
		return custTransactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, Timestamp.from(Instant.now()), lastMonthTimeStamp);
	}

	private Optional<List<CustomerTransaction>> getRewardsForSecondLastMonth(Long customerId) {
		Timestamp lastMonthTimeStamp = getTimeStampForProvidedDuration(Constants.daysInMonths*2);
		
//		return custTransactionRepository.findTransactions(customerId, Timestamp.from(Instant.now()).toString(), lastMonthTimeStamp.toString());
		return custTransactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, Timestamp.from(Instant.now()), lastMonthTimeStamp);
	}
	
	private Optional<List<CustomerTransaction>> getRewardsForThirdLastMonth(Long customerId) {
		Timestamp lastMonthTimeStamp = getTimeStampForProvidedDuration(Constants.daysInMonths*3);
//		return custTransactionRepository.findTransactions(customerId, Timestamp.from(Instant.now()).toString(), lastMonthTimeStamp.toString());
		return custTransactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, Timestamp.from(Instant.now()), lastMonthTimeStamp);
	}
	
	private Timestamp getTimeStampForProvidedDuration(long days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}
	
	private Long getRewardsPerMonth(Optional<List<CustomerTransaction>> custTranList) {
		if(custTranList.isPresent()) {
			return custTranList.get().stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
		}
		return 0l;
	}
	
	private Long calculateRewards(CustomerTransaction t) {
		if (t.getTransactionAmount() > Constants.rewardLimitOne && t.getTransactionAmount() <= Constants.rewardLimitTwo) {
			return Math.round(t.getTransactionAmount() - Constants.rewardLimitOne);
		} else if (t.getTransactionAmount() > Constants.rewardLimitTwo) {
			return Math.round(t.getTransactionAmount() - Constants.rewardLimitTwo) * 2
					+ (Constants.rewardLimitTwo - Constants.rewardLimitOne);
		} else
			return 0l;

	}

}
