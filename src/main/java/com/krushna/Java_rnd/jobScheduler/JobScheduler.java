package com.krushna.Java_rnd.jobScheduler;

import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class JobScheduler {
	
	boolean running=true;
	
	PriorityQueue<ScheduleJob> jobQueue = new PriorityQueue<>(
			Comparator.comparing( ScheduleJob::getNextRunInSeconds) );
	
	public void addJobs(ScheduleJob scheduleJob) {
		
		synchronized (jobQueue) {
			jobQueue.add(scheduleJob);
			jobQueue.notifyAll();
			
		}
	}
	public void runJobs() throws InterruptedException {
		while (running) {
			ScheduleJob job;
			
			synchronized (jobQueue) {
				if(jobQueue.isEmpty()) {
					jobQueue.wait();
				}
				job = jobQueue.peek();

				long nowInSeconds = new Date().getTime() / 1000;
				if ( job.nextRunInSeconds > nowInSeconds ) {
					jobQueue.wait( job.nextRunInSeconds- nowInSeconds);
					continue;
				}
				jobQueue.poll();
				
			}
			// run the job outside synchronized block
			
			job.job.run();
			
			
		}
	}
	
	public void stopScheduling() {
		running=false;
	}
	
	

}
