package com.atlassian.codedesign.ticketingSystem;

public class Agent {
	
	int agentId;
	double averageRating;
	int totalRating;
	int totalNumberOfreview;
	
	
	public Agent(int agentId, double averageRating, int totalRating, int totalNumberOfreview) {
		super();
		this.agentId = agentId;
		this.averageRating = averageRating;
		this.totalRating = totalRating;
		this.totalNumberOfreview = totalNumberOfreview;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public int getTotalRating() {
		return totalRating;
	}
	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
		totalNumberOfreview+=1;
		averageRating=(double)totalRating/totalNumberOfreview;
		
	}
	public int getTotalNumberOfreview() {
		return totalNumberOfreview;
	}
	public void setTotalNumberOfreview(int totalNumberOfreview) {
		this.totalNumberOfreview = totalNumberOfreview;
	}
	@Override
	public int hashCode() {
		return agentId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		return agentId == other.agentId;
				
	}
	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + ", averageRating=" + averageRating + ", totalRating=" + totalRating
				+ ", totalNumberOfreview=" + totalNumberOfreview + "]";
	}
	
	
	
	
	

}
