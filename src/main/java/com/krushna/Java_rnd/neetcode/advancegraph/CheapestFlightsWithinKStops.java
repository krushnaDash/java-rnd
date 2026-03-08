package com.krushna.Java_rnd.neetcode.advancegraph;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 * There are n airports, labeled from 0 to n - 1, which are connected by some flights.
 * You are given an array flights where flights[i] = [from_i, to_i, price_i]
 * represents a one-way flight from airport from_i to airport to_i with cost price_i.
 * You may assume there are no duplicate flights and no flights from an airport to itself.
 *
 * You are also given three integers src, dst, and k where:
 *
 *     src is the starting airport
 *     dst is the destination airport
 *     src != dst
 *     k is the maximum number of stops you can make (not including src and dst)
 *
 * Return the cheapest price from src to dst with at most k stops, or return -1 if it is impossible.
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 */
public class CheapestFlightsWithinKStops {
    // K is max stop allowed
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // lets solve this bell-man ford algo
        int[] prices= new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        // src price is 0
        prices[src]=0;
        // lets iterate of to K level
        for(int i=0; i< k+1; ++i){
            int[] tempPrice= Arrays.copyOf(prices,n);
            // iterate for all the edges to update prices
            for(int[] edge: flights){
                int s=edge[0]; // source
                int d=edge[1]; // destination
                int p=edge[2];
                if(prices[s] == Integer.MAX_VALUE ) { // inifinte price skip it
                    continue;
                }
                if(prices[s]+p < tempPrice[d]){
                    // update the temp price
                    tempPrice[d]=prices[s]+p;
                }
            }
            prices=tempPrice;
        }
        return  prices[dst]==Integer.MAX_VALUE ? -1: prices[dst];
    }


    public int findCheapestPricev2(int n, int[][] flights, int src, int dst, int k) {
    // build the adjacency list with Map
        Map<Integer, List<int[]>> nodesMap= new HashMap<>();
        for(int[] node : flights){
            nodesMap.computeIfAbsent(node[0], key -> new ArrayList<>()).add(new int[] {node[1], node[2]});
        }
        // 0 -> source, 1 -> destination 2-> cost
        int cost=BFSwithDJI(src, dst, k, nodesMap, n);
        return cost;
    }



    /**
     * We will use the Dijkastra algo here with BFS and minHeap
     * Key insight: Track stops per path, not globally
     * Each state in queue: [node, cost, stops]
     */
    public int BFSwithDJI(int src, int dst, int k, Map<Integer, List<int[]>> nodesMap, int n){
        // Track minimum cost to reach each node at each stop count
        // This allows us to revisit nodes if we find a cheaper path
        int[][] minCost = new int[n][k + 2];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Queue state: [node, cost, stops]
        // Priority by cost (Dijkstra's algorithm)
        Queue<int[]> nodeQueue= new PriorityQueue<>((int[] a, int[] b) -> a[1] - b[1]);
        nodeQueue.offer(new int[] {src, 0, 0});
        minCost[src][0] = 0;

        while (!nodeQueue.isEmpty()){
            int[] node = nodeQueue.poll();
            int currentNode = node[0];
            int currentCost = node[1];
            int currentStops = node[2];

            // Found destination
            if(currentNode == dst){
                return currentCost;
            }

            // Exceeded stop limit (k stops means k+1 flights/edges)
            if(currentStops > k){
                continue;
            }

            // Explore neighbors
            if(nodesMap.containsKey(currentNode)){
                for(int[] neighbor: nodesMap.get(currentNode)){
                    int nextNode = neighbor[0];
                    int edgeCost = neighbor[1];
                    int newCost = currentCost + edgeCost;
                    int newStops = currentStops + 1;

                    // Only add to queue if this is a cheaper way to reach nextNode with newStops stops
                    // This prevents processing redundant paths while allowing valid exploration
                    if(newCost < minCost[nextNode][newStops]){
                        minCost[nextNode][newStops] = newCost;
                        nodeQueue.offer(new int[] {nextNode, newCost, newStops});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] flights= {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        CheapestFlightsWithinKStops cf= new CheapestFlightsWithinKStops();
        System.out.println("Test 1 - Expected: 700, Got: " + cf.findCheapestPrice(4,flights,0,3,1));

        // Test 2: Path should be 0->1->2->3 with cost 1+1+1=3
        int[][] flights2= {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        System.out.println("Test 2 - Expected: 3, Got: " + cf.findCheapestPrice(4,flights2,0,3,2));
    }

}
