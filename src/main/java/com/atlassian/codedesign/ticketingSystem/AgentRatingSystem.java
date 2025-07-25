package com.atlassian.codedesign.ticketingSystem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * Imagine we have a customer support ticketing system. The system allows
 * customers to rate the support agent. To start with, write a function which
 * accepts a rating, and another which will get all of the agents and the
 * average rating each one has received, ordered highest to lowest.
 */

public class AgentRatingSystem {
	
	
	Map<Integer, Agent> agentMap= new HashMap<Integer, Agent>();
	PriorityQueue<Agent> agentList= new PriorityQueue<Agent>(Comparator.comparing(Agent::getAverageRating, Comparator.reverseOrder()));

	/**
	 * This will take the rating from the customer and update
	 * 
	 * @param agentId
	 * @param rating  log n
	 */
	// 100 K TPM
	public Agent submitRating(int agentId, int rating) {
		Agent agent=null;
		if(agentMap.get(agentId) == null) {
			 agent= new Agent(agentId, rating, rating, 1);
			 agentList.add(agent);
		}else {
			agent=agentMap.get(agentId);
			agentList.remove(agent);
			agent.setTotalRating(agent.getTotalRating()+rating);
			agentList.add(agent);
			
		}
		agentMap.put(agentId, agent);
		return agent;
	}

	// Sroted List
	// 1 TPM
	// nlongn
	public List<Agent> getAllAgent() {
		return agentList.stream().toList();
	}
	
	public Agent getAgent(int agentId) {
		return agentMap.get(agentId);
	}
	
	public static void main(String[] args) {
       AgentRatingSystem ratingSystem= new AgentRatingSystem();
       
       
		
		ratingSystem.submitRating(1001, 3);
		ratingSystem.submitRating(1001, 5);
		
		
		ratingSystem.submitRating(1002, 2);
		ratingSystem.submitRating(1002, 2);
		
		ratingSystem.submitRating(1001, 3);
		ratingSystem.submitRating(1004, 5);
		
		List<Agent> sortedAgentList=ratingSystem.getAllAgent();
		System.out.println(sortedAgentList);
		System.out.println(ratingSystem.agentList.poll());
		System.out.println(ratingSystem.agentList.poll());
		System.out.println(ratingSystem.agentList.poll());
	}
	

}
