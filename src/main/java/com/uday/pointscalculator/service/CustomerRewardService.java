package com.uday.pointscalculator.service;

import com.uday.pointscalculator.dto.CustomerRewardsResponseDto;

public interface CustomerRewardService {
	
	CustomerRewardsResponseDto getRewardPointsByCustomerId(Long customerId); 

}
