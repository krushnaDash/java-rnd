package com.krushna.Java_rnd.rateLimit;

import java.util.TreeSet;

public class LimitConstatn {
	
	public static final int WINDOW_SIZE_IN_SECONDS=10;
	public static final int REQUEST_LIMIT=5;
	
	
	// These code are not used, just another thought how a treeSet can be used for rate limiter
	
	TreeSet<Long> requestTimes = new TreeSet<>();

	public boolean allowRequest(long now) {
	    long window = 10_000; // 10 seconds window
	    requestTimes.add(now);
	    requestTimes = new TreeSet<>(requestTimes.tailSet(now - window));
	    return requestTimes.size() <= 100;
	}
	

}
