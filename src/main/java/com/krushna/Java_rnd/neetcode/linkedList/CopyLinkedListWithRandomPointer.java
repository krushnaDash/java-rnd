package com.krushna.Java_rnd.neetcode.linkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 https://leetcode.com/problems/copy-list-with-random-pointer/description/
 
 A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.

Your code will only be given the head of the original linked list.
 */

public class CopyLinkedListWithRandomPointer {

	
	
	/**
	 * We need to do deep copy
	 * how to have the random one
	 * 
	 * 
	 */
	public Node copyRandomList(Node head) {
        Node newHead= null;
        Node previous=null;
        
        // if there is no random, we could have done like below
        // how to copy the random
        // lets create a HashMap and store the original and copy node mapping
        Node ptr=head;
        Map<Node,Node> nodeMap= new HashMap<>();
        while(ptr !=null) {
        	Node tempNode = new Node(ptr.val);
        	nodeMap.put(ptr, tempNode); // original to copy
        	if(previous !=null) {
        		previous.next=tempNode;
        	}else {
        		newHead=tempNode;
        	}
        	previous=tempNode;
        	ptr=ptr.next;
        }
        // iterate again to update the random
        
        Node p1=head;
        Node p2=newHead;
        
        while(p1 !=null) {
        	if(p1.random != null) {
        		p2.random=nodeMap.get(p1.random);
        	}
        	p1=p1.next;
        	p2=p2.next;
        }
        
        return newHead;
		
    }
	
	
	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

}
