package com.krushna.Java_rnd.jobScheduler;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ScheduleJob {

	UUID jobId;
	Runnable job;
	ZonedDateTime startTime;
	long nextRunInSeconds;

	public ScheduleJob(UUID jobId, Runnable job, ZonedDateTime startTime) {
		super();
		this.jobId = jobId;
		this.job = job;
		this.startTime = startTime;
		nextRunInSeconds=startTime.toEpochSecond();
	}

	public UUID getJobId() {
		return jobId;
	}

	public void setJobId(UUID jobId) {
		this.jobId = jobId;
	}

	public Runnable getJob() {
		return job;
	}

	public void setJob(Runnable job) {
		this.job = job;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public long getNextRunInSeconds() {
		return nextRunInSeconds;
	}

	public void setNextRunInSeconds(long nextRunInSeconds) {
		this.nextRunInSeconds = nextRunInSeconds;
	}
	
	

}
