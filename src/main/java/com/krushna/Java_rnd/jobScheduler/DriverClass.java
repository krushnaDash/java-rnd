package com.krushna.Java_rnd.jobScheduler;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class DriverClass {
	public static void main(String[] args) throws InterruptedException {
		ZonedDateTime now= ZonedDateTime.now();
		now=now.plusSeconds(20);
		JobScheduler jobScheduler= new JobScheduler();
		ScheduleJob myScheduleJob= new ScheduleJob(UUID.randomUUID(), () -> System.out.println("Running job now " + new Date()), now);
		jobScheduler.addJobs(myScheduleJob);
		jobScheduler.runJobs();
		System.out.println("Done");
		
	}

}
