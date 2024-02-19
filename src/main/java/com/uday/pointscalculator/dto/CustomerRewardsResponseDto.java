package com.uday.pointscalculator.dto;

public class CustomerRewardsResponseDto {
	
	private Long customerId;
	private Long rewardPointsMonthOne;
	private Long rewardPointsMonthTwo;
	private Long rewardPointsMonthThree;
	private Long rewardPointsTotal;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getRewardPointsMonthOne() {
		return rewardPointsMonthOne;
	}
	public void setRewardPointsMonthOne(Long rewardPointsMonthOne) {
		this.rewardPointsMonthOne = rewardPointsMonthOne;
	}
	public Long getRewardPointsMonthTwo() {
		return rewardPointsMonthTwo;
	}
	public void setRewardPointsMonthTwo(Long rewardPointsMonthTwo) {
		this.rewardPointsMonthTwo = rewardPointsMonthTwo;
	}
	public Long getRewardPointsMonthThree() {
		return rewardPointsMonthThree;
	}
	public void setRewardPointsMonthThree(Long rewardPointsMonthThree) {
		this.rewardPointsMonthThree = rewardPointsMonthThree;
	}
	public Long getRewardPointsTotal() {
		return rewardPointsTotal;
	}
	public void setRewardPointsTotal(Long rewardPointsTotal) {
		this.rewardPointsTotal = rewardPointsTotal;
	}

}
