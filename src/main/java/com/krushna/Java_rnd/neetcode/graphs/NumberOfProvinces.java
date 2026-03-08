package com.krushna.Java_rnd.neetcode.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 https://leetcode.com/problems/number-of-provinces/submissions/1927478029/
 
 There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 */
public class NumberOfProvinces {
	
	
	// this also can be solve by  find and union
	public int findCircleNum(int[][] isConnected) {
        int res=isConnected.length;
		// create the edges with rank and parent array
        List<int[]> edges= new ArrayList<int[]>();
        int[] parent= new int[res];
        int[] rank= new int[res];
        
        for(int i=0; i< isConnected.length; ++i) {
        	parent[i]=i;
        	rank[i]=1;
        	for(int j=0; j< isConnected[i].length; ++j) {
        		if(i!=j && isConnected[i][j]==1)
        			edges.add( new int[] {i,j});
        	}
        }
        // check all edge and union them
        for(int[] edge: edges) {
        	res-= union(edge[0], edge[1], parent, rank);
        }
		return res;
    }
	
	public int find(int node, int[] parent) {
		if(node== parent[node]) {
			return node;
		}
		// lets do some path compression 
		parent[node]= parent[parent[node]];
		
		return find(parent[node], parent);
	}
	
	public int union(int n1, int n2, int [] parent, int[] rank) {
		int p1= find(n1, parent);
		int p2= find(n2, parent);
		if(p1==p2) {
			return 0;
		}
		if(rank[p1]> rank[p2]) {
			// union p1 with p2
			rank[p1]= rank[p1]+rank[p2];
			parent[p2]=p1;
			
		}else {
			rank[p2]= rank[p1]+rank[p2];
			parent[p1]=p2;
		}
		return 1;
	}
	

}
