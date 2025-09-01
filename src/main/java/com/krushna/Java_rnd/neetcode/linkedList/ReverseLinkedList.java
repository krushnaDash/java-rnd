package com.krushna.Java_rnd.neetcode.linkedList;

/**
 * https://leetcode.com/problems/reverse-linked-list/description/
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {
	
    public ListNode reverseList(ListNode head) {
    	ListNode prevNode=null;
    	while(head !=null) {
    		ListNode currentNode=head;
    		head=head.next;
    		currentNode.next=prevNode;
    		prevNode=currentNode;
    	}
    	
        return prevNode;
    }
    
     

}
