package com.krushna.Java_rnd.sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given a social network represented as an undirected graph. Each node
 * represents a user, and each edge represents a bi-directional friendship
 * between users.
 * 
 * 
 * 
 * Given:
 * 
 * nodes: the total number of users (numbered from 1 to nodes)
 * 
 * Two lists u and v where each (u[i], v[i]) denotes a friendship between user
 * u[i] and user v[i]
 * 
 * A list of queries, where each query is a user ID
 * 
 * 
 * 
 * Task: For each query, return the number of users in the same connected
 * component as the queried user (including the user themself). In other words,
 * return the number of profiles visible to each queried user.
 * 
 * nodes = 7
 * u = [1, 2, 3, 5]
 * v = [2, 3, 4, 6]
 * queries = [1, 3, 5, 7]
 * 
 * 
 * 
 */

public class VisibleProfilesCounter {
	
	
	
	public static List<Integer> getVisibleProfilesCount(int nodes, List<Integer> u, List<Integer> v,
			List<Integer> queries) {
		
		List<Integer> visibleProfileCount= new ArrayList<>();
		// use 2d array to represent the graph, can be used Map also, lets use Map for simplicity 
		Map<Integer, Set<Integer>> userGaph= new HashMap<>();
		
		// populate the userGraph , since it is bidirectional we need add both side
		for(int i=0; i< u.size(); ++i) {
			userGaph.computeIfAbsent(u.get(i), ignore -> new HashSet<>()).add(v.get(i));
			userGaph.computeIfAbsent(v.get(i), ignore -> new HashSet<>()).add(u.get(i));
		}
		
		for( int i : queries) {
			// do BFS or DFS to get the count
			visibleProfileCount.add(bfs(userGaph, i));
		}
		
		return visibleProfileCount;
		
	}
	
	public static int bfs(Map<Integer, Set<Integer>> userGaph, int node) {
		int count = 0;// make it 0, since the current node will get added
		Queue<Integer> nodes = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		if (userGaph.get(node) != null) {
			nodes.addAll(userGaph.get(node));
			while (!nodes.isEmpty()) {
				int user = nodes.poll();
				if (!visited.contains(user)) {
					count++;
					visited.add(user);
					if (userGaph.get(user) != null)
						nodes.addAll(userGaph.get(user));
				}
			}
			return count;
		} else {
			return 1;
		}

	}
	// can be done we need pass the visited as part of the method
	
	public static int dfs(Map<Integer, Set<Integer>> userGaph, int node) {
		int count = 0;// make it 0, since the current node will get added
		Set<Integer> visited = new HashSet<>();
		if (userGaph.get(node) != null) {
			
			return count;
		} else {
			return 1;
		}

	}
	
	
	public static void main(String[] args) {
		System.out.println( getVisibleProfilesCount(7, 
				Arrays.asList(1, 2, 3, 5), 
				Arrays.asList(2, 3, 4, 6), 
				Arrays.asList(1, 3, 5, 7)));
	}

}
