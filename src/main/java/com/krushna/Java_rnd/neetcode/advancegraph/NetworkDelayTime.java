package com.krushna.Java_rnd.neetcode.advancegraph;

import java.util.*;

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given times,
 * a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node,
 * and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // create an adjacencey list with node and weight
        Map<Integer, List<int[]>> nodeMap= new HashMap<>();
        for(int[] node: times){
            nodeMap.computeIfAbsent(node[0], key -> new ArrayList<>()).add(new int [] {node[1], node[2]});
        }
        // do BFS from the k th node find the total time
        return  findTImeBFS(k, nodeMap,n);
    }
    // All nodes needs to recived the signal hence we need to complete the BFS
    // We need to use the Dijkstra's shortest path algo to solve this.
    public int findTImeBFS(int k, Map<Integer, List<int[]>> nodeMap, int n){
        Queue<int[]> nodesQ= new PriorityQueue<>( (int[] a, int[] b) -> a[1]-b[1]); // by their path weight
        // nodes from 1 to n
        boolean[] visited= new boolean[n+1];
        // add the K node to the minHeap
        // 0 for the K node
        nodesQ.offer(new int[]{k,0});
        int lastNodeTime=0;
        // Lets have visited count so that we can do early return if all the nodes are visited
        int visitCount=0;
        while(!nodesQ.isEmpty()){
            int maxTime= 0;
            // pull the node with min cost path value
            int[] node= nodesQ.poll();

            if(visited[node[0]]){
                continue;
            }
            visited[node[0]]=true;
            lastNodeTime= node[1];
            visitCount++;
            if(visitCount == n) return  lastNodeTime;
            // get all the child nodes offer to minHeap by updating their path cost
            if(nodeMap.get(node[0]) !=null) {
                for (int[] nNode : nodeMap.get(node[0])) {
                    nNode[1]=nNode[1]+ node[1]; // add current node path cost
                    nodesQ.offer(nNode);
                }
            }
        }
        // If not all nodes were reached
        return (visitCount == n) ? (int) lastNodeTime : -1;
    }

    public static void main(String[] args) {
        int[][] nodes= {{2,1,1},{2,3,1},{3,4,1}};
        int n=4;
        int k=2;
        NetworkDelayTime nd= new NetworkDelayTime();
        System.out.println(nd.networkDelayTime(nodes,n,k));

    }

}
