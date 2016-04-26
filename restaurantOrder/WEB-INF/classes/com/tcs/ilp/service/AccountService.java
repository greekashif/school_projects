package com.tcs.ilp.service;

public class AccountService {
	
	public static float calculateInterest(float deposit) {
		float interest = deposit < 1000.0 ? 0.0f : deposit < 5000.0 ? 
					5.0f : deposit < 10000.0 ? 7.0f : 10.0f;
		return deposit * interest;
	}

}
