package com.krushna.Java_rnd.rateLimit;

import java.util.Date;

public class Customer {
	int customerId;
	int requestCount;
	long windowStartSeconds;
	int credit;
	public Customer(int customerId) {
		this.customerId=customerId;
		requestCount=0;
		windowStartSeconds=new Date().getTime()/1000;
		credit=0;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	public long getWindowStartSeconds() {
		return windowStartSeconds;
	}
	public void setWindowStartSeconds(long windowStartSeconds) {
		this.windowStartSeconds = windowStartSeconds;
	}
	
	

}
