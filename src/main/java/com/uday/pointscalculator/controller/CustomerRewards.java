package com.uday.pointscalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uday.pointscalculator.dto.CustomerRewardsResponseDto;
import com.uday.pointscalculator.service.CustomerRewardService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerRewards {
	
	@Autowired
	private CustomerRewardService custRewardService;
	
	@GetMapping(path = "/{customerId}/rewardpoints", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerRewardsResponseDto getRewardPointsByCustomerId(@PathVariable long customerId) {
		return custRewardService.getRewardPointsByCustomerId(customerId);
	}
}
