package com.atlassian.codedesign.ticketingSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AgentRatingSyatemBinarySearch {

	Map<Integer, Agent> agentMap = new HashMap<Integer, Agent>();
	List<Agent> agentList = new LinkedList<>();

	/**
	 * This will take the rating from the customer and update
	 * 
	 * @param agentId
	 * @param rating  log n
	 */
	// 100 K TPM
	public Agent submitRating(int agentId, int rating) {
		Agent agent = null;
		if (agentMap.get(agentId) == null) {
			agent = new Agent(agentId, rating, rating, 1);
		} else {
			agent = agentMap.get(agentId);
		}

		agentList.remove(agent);
		agent.setTotalRating(agent.getTotalRating() + rating);
		int position = binarySearchToFindPostion(agentList, agent);
		agentList.add(position, agent);
		agentMap.put(agentId, agent);
		return agent;
	}

	// Sroted List
	// 1 TPM
	// nlongn
	public List<Agent> getAllAgent() {
		return agentList;
	}

	public Agent getAgent(int agentId) {
		return agentMap.get(agentId);
	}

	public int binarySearchToFindPostion(List<Agent> agentList, Agent newAgent) {
		int low = 0;
		int high = agentList.size() - 1;
		// one element
		if (low == high) {
			if (agentList.get(low).getAverageRating() < newAgent.averageRating)
				return 0;
			else {
				return 1;
			}
		} else {
			while (low <= high) {
				int mid = (low + high) / 2;

				if (agentList.get(mid).getAverageRating() < newAgent.averageRating)
					high = mid - 1;
				else
					low = mid + 1;

			}
			return low;
		}

	}

	public static void main(String[] args) {
		AgentRatingSyatemBinarySearch ratingSystem = new AgentRatingSyatemBinarySearch();

		ratingSystem.submitRating(1001, 3);
		ratingSystem.submitRating(1001, 5);

		ratingSystem.submitRating(1002, 2);
		ratingSystem.submitRating(1002, 2);

		ratingSystem.submitRating(1001, 3);
		ratingSystem.submitRating(1004, 5);

		List<Agent> sortedAgentList = ratingSystem.getAllAgent();
		System.out.println(sortedAgentList);
	
	}

}
