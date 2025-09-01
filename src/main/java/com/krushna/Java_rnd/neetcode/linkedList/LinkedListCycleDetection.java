package com.krushna.Java_rnd.neetcode.linkedList;

/**
 * https://leetcode.com/problems/linked-list-cycle/description/
 * 
 * Given head, the head of a linked list, determine if the linked list has a
 * cycle in it.
 * 
 * There is a cycle in a linked list if there is some node in the list that can
 * be reached again by continuously following the next pointer. Internally, pos
 * is used to denote the index of the node that tail's next pointer is connected
 * to. Note that pos is not passed as a parameter.
 * 
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * 
 * 
 * 
 */

public class LinkedListCycleDetection {

	
    // lets try to solve using two pointer fast pointer and slow pointer, if they meet then there is a cycle
	public boolean hasCycle(ListNode head) {
     ListNode fp=head;
     ListNode sp=head;
     
     while(fp != null && sp !=null) {
    	 sp=sp.next;
    	 if(fp.next == null)
    		 return false;
    	 
    	 fp=fp.next.next;
    	 
    	 if(fp==sp) {
    		 return true;
    	 }
     }
     return false;
    
    }

	
}
