package com.krushna.Java_rnd.neetcode.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloneGraph {
	Set<Node> visitedNode= new HashSet<Node>();
	Map<Integer, Node> nodeMap= new HashMap<Integer, Node>();
	
	public Node cloneGraph(Node node) {
        Node clone=null;
        if(node !=null){
		 clone= new Node(node.val);
		 nodeMap.put(clone.val, clone);
		 copyWithDFS(node, clone);
        }
		return clone;
	}
	
	public void copyWithDFS(Node node, Node copyNode) {
		// base case
		if(visitedNode.contains(node)) {
			return;
		}
		visitedNode.add(node);
		
		for(Node c: node.neighbors) {
			// we need to use a map to store created node, so that we can use them
			Node cc=nodeMap.computeIfAbsent(c.val, (key -> new Node(key)));
			copyNode.neighbors.add(cc);
			copyWithDFS(c, cc);
		}
	}

}
