package com.krushna.Java_rnd.neetcode.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfConnectedComponents {
	/**
	 * You have a graph of n nodes. You are given an integer n and an array edges
	 * where edges[i] = [aᵢ, bᵢ] indicates that there is an edge between aᵢ and bᵢ
	 * in the graph. Return the number of connected components in the graph.
	 * Solve LeetCode 547
	 */
	public int countComponents(int n, int[][] edges) {
		int count=0;
		// create the adjacency List for both way
		Map<Integer, List<Integer>> edgeMap= new HashMap<Integer, List<Integer>>();
		for(int e[]: edges) {
			edgeMap.computeIfAbsent(e[0], key -> new ArrayList<>()).add(e[1]);
			edgeMap.computeIfAbsent(e[1], key -> new ArrayList<>()).add(e[0]);
		}
		// lets use visited nodes boolean 
		boolean[] visited= new boolean[n];
		
		for(int i=0; i< n; ++i) {
			if(! visited[i]) {
			// do DFS remove the nodes are completed
				++count;
				dfs(edgeMap, i, visited);
			}
		}
	
		return count;
    }
	public void dfs(Map<Integer, List<Integer>> edgeMap, int node, boolean[] visited) {
		visited[node]=true;
		if(edgeMap.get(node) == null) {
			return;
		}
	
		for(int n: edgeMap.get(node)) {
			if( !visited[n])
				dfs(edgeMap, n, visited);
		}
	}
	
	// another solution to solve this is union and find
	
	public int countComponentsv2(int n, int[][] edges) {
		// create two array
		int[] parent= new int [n];
		int[] rank= new int[n];
		// fill them, each node is there parent
		for(int i=0; i< n; ++i) {
			parent[i]=i;
			rank[i]=1;
		}
		int res=n;
		for(int[] edge: edges) {
			res-= union(edge[0], edge[1], parent, rank);
		}
		return res;
	}
	// find root parent for a node
	public int find(int node, int[] parent) {
		if(parent[node]== node)
			return node;
		return find(parent[node], parent);
	}
	//
	public int union(int n1, int n2, int[] parent, int[] rank) {
		// both parent is same return 0, means they are already part of same graph
		int p1= find(n1, parent);
		int p2= find(n2, parent);
		if(p1==p2) {
			return 0;
		}
		// find the rank
		if(rank[p1] > rank[p2]) {
			// union to p2 to p1
			rank[p1]= rank[p1]+rank[p2];
			parent[p2]=p1;
		}else {
			rank[p2]= rank[p1]+rank[p2];
			parent[p1]=p2;
		}
		return 1;
		
	}
	

}
