package com.krushna.Java_rnd.neetcode.linkedList;
import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 Implement the LRUCache class:
 *
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 The functions get and put must each run in O(1) average time complexity.
 *
 https://leetcode.com/problems/lru-cache/description/
 *
*/
public class LRUCache {
	Map<Integer, Node> cache; 
	Node head;
	Node tail;
	int capacity;
    

    public LRUCache(int capacity) {
    	cache=  new HashMap<>();
    	this.capacity=capacity;
    	// Lets have two dummy node at begning and end this will be point by head and tail
    	// this will make the code clean and no need take care of edge cases of single node 
    	//and moving the head and tail pointer
    	head= new Node(-1, -1);
    	tail= new Node(-1, -1);
    	head.next=tail;
    	tail.prev=head;
    }

    public int get(int key) {
       Node node=cache.get(key);
       if(node != null) {
    	 moveToFront(node);
         return node.value;
       }else {
    	   return -1;
       }
    }

    public void put(int key, int value) {
       Node node= cache.get(key);
       if(node != null) {
    	   moveToFront(node);
    	   node.value=value;
       }else {
    	   if(cache .size()>= capacity) {
    		   // remove from tail
    		   int keyToremove=removeFromTail();
    		   cache.remove(keyToremove);
    	   }
    	   Node newNode= new Node(key, value);
    	   cache.put(key, newNode);
    	   insetAtHead(newNode);
       }
    	   
    }
    
    // lets have some method
    
    public void moveToFront(Node node) {
    	removeNode(node);
    	insetAtHead(node);
    }
    // 1-> 2-> 3-> 4
    public void removeNode(Node node) {
    	Node prevNode=node.prev;
    	Node nextNode=node.next;
    	prevNode.next=nextNode;
    	nextNode.prev=prevNode;
    	node.next=null;
    	node.prev=null;
    }
    // -1 ->2-> -1
    public void insetAtHead(Node node) {
    	Node currentNode= head.next;
    	head.next=node;
    	node.prev=head;
    	node.next=currentNode;
    	currentNode.prev=node;
    	
    }
    public int  removeFromTail() {
    	Node currentNode= tail.prev;
    	removeNode(currentNode);
        return currentNode.key;
    }
   

    public static void main(String[] args) {
        // Basic scenario
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // [1]
        lruCache.put(2, 2); // [2,1]
        System.out.println(lruCache.get(1)); // 1 -> [1,2]
        lruCache.put(3, 3); // evict 2 -> [3,1]
        System.out.println(lruCache.get(2)); // -1
        lruCache.put(4, 4); // evict 1 -> [4,3]
        System.out.println(lruCache.get(1)); // -1
        System.out.println(lruCache.get(3)); // 3
        System.out.println(lruCache.get(4)); // 4

        // Single capacity scenario
        LRUCache lruCache1 = new LRUCache(1);
        lruCache1.put(2, 1); // [2]
        System.out.println(lruCache1.get(2)); // 1
        lruCache1.put(3, 2); // evict 2 -> [3]
        System.out.println(lruCache1.get(2)); // -1
        System.out.println(lruCache1.get(3)); // 2

        // Capacity 0 scenario (edge case)
        LRUCache zeroCap = new LRUCache(0);
        zeroCap.put(10, 100);
        System.out.println(zeroCap.get(10)); // -1, cannot store
    }

    // We need have double linked list to keep track of the order of usage
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
