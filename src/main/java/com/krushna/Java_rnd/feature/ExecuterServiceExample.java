package com.krushna.Java_rnd.feature;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * It’s built for IO-bound, blocking, long-lived tasks. Why only blocking tasks?
 * 
 */
public class ExecuterServiceExample {
	
	public static void main(String[] args) {
		ExecutorService executorService= Executors.newFixedThreadPool(5);
		ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(5);
		//scheduledExecutorService.schedule(null, 0, null)
		
		
		
		
		
	}

}
