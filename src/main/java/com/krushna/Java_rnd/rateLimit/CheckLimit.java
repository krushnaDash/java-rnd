package com.krushna.Java_rnd.rateLimit;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckLimit {
	
	Map<Integer, Customer> customerState= new ConcurrentHashMap<Integer, Customer>();

	// return true if the its within limit
	/**
	 * Imagine we are building an application that is used by many different
	 * customers. We want to avoid one customer being able to overload the system by
	 * sending too many requests, so we enforce a per-customer rate limit. The rate
	 * limit is defined as:
	 * 
	 * “Each customer can make X requests per Y seconds”
	 * 
	 * Assuming that customer ID is extracted somehow from the request, implement
	 * the following function.
	 * 
	 * 
	 * // Perform rate limiting logic for provided customer ID. Return true if the
	 * // request is allowed, and false if it is not.
	 * 
	 * @param customerId
	 * @return
	 */
	boolean rateLimit(int customerId) {
		
		// pull the customer state
		Customer customer=customerState.computeIfAbsent(customerId, Customer::new);
		// check for new window size to reset the count
		long timeNowInSeconds=new Date().getTime()/1000;
		if(timeNowInSeconds-customer.windowStartSeconds >= LimitConstatn.WINDOW_SIZE_IN_SECONDS) {
			customer.requestCount=1;
			return true;
		}else {
			if(customer.requestCount < LimitConstatn.REQUEST_LIMIT) {
				customer.requestCount++;
				return true;
			}else {
				return false;
			}
			
		}
		
		
	}

}
