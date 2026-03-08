package com.krushna.Java_rnd.neetcode.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**

https://neetcode.io/problems/valid-tree/question?list=neetcode150

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input:
n = 5
edges = [[0, 1], [0, 2], [0, 3], [1, 4]]

Output:
true

Example 2:

Input:
n = 5
edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]

Output:
false
 */
public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		// create the adjacency map, not it is undirected graph so we need store both way
		Map<Integer, List<Integer>> edgeMap= new HashMap<Integer, List<Integer>>();
		
		
		for(int [] edge: edges) {
			edgeMap.computeIfAbsent(edge[0],  (key) -> new ArrayList<>()).add(edge[1]);
			edgeMap.computeIfAbsent(edge[1],  (key) -> new ArrayList<>()).add(edge[0]);
		}
		Set<Integer> childSet= new HashSet<Integer>();
		
		boolean hasCycle=checkCycle(edgeMap, 0, childSet, -1); // previous node as -1 and start from 0
			
		
		return ! hasCycle ? childSet.size()==n : false;
	}
	// 0 -> 1,2,3  1-> 0,4   2->0, 3->0,  4->1
	public boolean checkCycle(Map<Integer, List<Integer>> cMap, int node, Set<Integer> childSet, int prevNode) {
		// node=0
		if(childSet.contains(node)) {
			return true; // cycle
		}
		// add the current node
		childSet.add(node);
		
		// if this has any child
		if(cMap.get(node) != null) {
			// 1,2,3
			for(int n: cMap.get(node)) {
				if(n== prevNode)
					continue; // skip
				if(checkCycle(cMap, n, childSet,node)) // n=1, prevNode=0 
					return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int [][] edges= new int[][] {{0,1},{1,2},{2,3},{1,3},{1,4}};
		GraphValidTree gt= new GraphValidTree();
		System.out.println(gt.validTree(5, edges));
	}

}
