package com.krushna.Java_rnd.neetcode.advancegraph;

import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 *     For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 *
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once
 */
public class ReconstructItinerary {
    // more optimised using the Hierholzer's Algorithm, using  Eulerian Path

    public List<String> findItineraryv2(List<List<String>> tickets) {
        // build the adjacency list with priority queue
        Map<String, Queue<String>> adjList= new HashMap<>();
        for(List<String> ticket: tickets){
            adjList.computeIfAbsent(ticket.get(0), key -> new PriorityQueue<>()).add(ticket.get(1));
        }
        List<String> res= new ArrayList<>();
        dfsv2(adjList, "JFK", res);
        Collections.reverse(res);
        return res;
    }
    public void dfsv2(Map<String, Queue<String>> adjList, String src, List<String> res){
        Queue<String> queue = adjList.get(src);
        while(queue !=null && !queue.isEmpty()){
            String dst = queue.poll();
            dfsv2(adjList, dst, res); // go till depth
        }
        res.add(src);
    }




    public List<String> findItinerary(List<List<String>> tickets) {
        // sort the input so that ticket, so that the adjacency list will have the sorted value.
        tickets.sort((a, b) -> a.get(1).compareTo(b.get(1)));

        // build the adjacency list
        Map<String, List<String>> adjList= new HashMap<>();
        for(List<String> ticket: tickets){
            adjList.computeIfAbsent(ticket.get(0), key -> new ArrayList<>()).add(ticket.get(1));
        }
        List<String> res= new ArrayList<>();
        // do DFS from the JFK node, and add the node to the result list when we
        // we always start from JFK node, so we can add it to the result list before the DFS
        res.add("JFK");
        return DFS("JFK", res,tickets.size(), adjList) ? res: Collections.emptyList();

    }
    // DFS with backtracking, if path did not give any result
    public boolean DFS(String start, List<String> res, int ticketSize,Map<String, List<String>> adjList ){
        // base case, if the res length same with ticket +1
        if(res.size() == ticketSize+1){
            return true;
        }

        if(!adjList.containsKey(start)) {
            return false;
        }
        // we we will remove the node from adjacency list once it is visited, hence no visited set
        // iterate over a copy list.
        List<String> adjNodes=adjList.get(start);
        List<String> copyNode = new ArrayList<>(adjNodes);
        for (int i=0; i< copyNode.size(); ++i) {
                // add to res and remove from the adjList
                adjNodes.remove(i);
                res.add(copyNode.get(i));
                boolean dfsValue= DFS( copyNode.get(i) ,res,ticketSize, adjList  );
                if(dfsValue) return  true;
                // the above path did not work do back track by adding the value
                adjNodes.add(i, copyNode.get(i));
                res.removeLast();
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(new ArrayList<>(Arrays.asList("JFK", "B")));
        tickets.add(new ArrayList<>(Arrays.asList("JFK", "C")));
        tickets.add(new ArrayList<>(Arrays.asList("C", "JFK")));
        ReconstructItinerary ri= new ReconstructItinerary();
        System.out.println(ri.findItinerary(tickets));

        System.out.println(ri.findItineraryv2(tickets));
    }
}
