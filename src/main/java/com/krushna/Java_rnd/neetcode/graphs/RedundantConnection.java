package com.krushna.Java_rnd.neetcode.graphs;

import java.util.Arrays;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input
 */
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        // we will use the find and Union algo, to solve this, if any edge create the cycle that can be removed.
        // since the nodes are form 1 to n, lets have the array from 1 to n and ignore the 0th one;
        int n=edges.length;
        int[] parent= new int[n+1];
        int[] rank= new int[n+1];
        for(int i=1; i <=n;++i){
            parent[i]=i;
            rank[i]=1;
        }
        // check all the edges
        for(int [] edge: edges){
            // check cycle
            if(! union(edge[0], edge[1], parent, rank)){
                return  edge;
            }
        }
        return null;
    }
    public int find(int n, int[] parent){
        if(n==parent[n])
            return n;
        // path compression
        parent[n]=parent[parent[n]];
       return  find(parent[n], parent);
    }
    public boolean union(int n1, int n2, int[] parent, int[] rank){
        int p1= find(n1, parent);
        int p2= find(n2, parent);
        // no more union, cycle detected return false
        if(p1==p2){
            return false;
        }
        if(rank[p1]>rank[p2]){
            parent[p2] = p1;
            rank[p1]=rank[p1]+rank[p2];
        }else {
            parent[p1] = p2;
            rank[p2]=rank[p1]+rank[p2];
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] edges= {{1,2},{1,3},{2,3}};
        RedundantConnection re= new RedundantConnection();
        System.out.println(Arrays.toString(re.findRedundantConnection(edges)));
    }

}
