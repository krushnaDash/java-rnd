package com.krushna.Java_rnd.neetcode.linkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reorder-list/description/
 * 
 * You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln

Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …

You may not modify the values in the list's nodes. Only nodes themselves may be changed.

 */

public class ReorderLinkedList {
	
	/**
	 * The solution here is find the half part of the linked list
	 * How to find the half part, using slow pointer and fast pointer
	 * the slow pointer position will be half when the fast pointer become null, this will work for both odd and even
	 * 
	 * Now once we found the half part, we need to reverse the second half and merge with first half
	 * 
	 * 
	 */
	
	public static void reorderListOp(ListNode head) {
		ListNode sp=head;
		ListNode fp=head.next;
		while(fp !=null && fp.next !=null ) {
			sp=sp.next;
			fp=fp.next.next;
		}
		
		// SP is left half and SP.next is the second half
		
		ListNode reversed=reverse(sp.next);
		// break the connection
		sp.next=null;
		merge(head,reversed);
		
	}
	
	/**
	 * 1, 2, 3, 4 
	 *
	 */
	public static ListNode reverse(ListNode head) {
		ListNode previous=null;
		ListNode ptr=head;
		
		while(ptr !=null) {
		   ListNode temp=ptr; 		
			ptr=ptr.next;
			temp.next=previous;
			previous=temp;
		}
		return previous;
	}
	
	public static ListNode merge(ListNode l1, ListNode l2) {
		if(l1==null)
			return l2;
		if(l2==null)
			return l1;
		
		ListNode p1=l1;
		ListNode p2=l2;
		
		while(p1!= null && p2 !=null) {
			ListNode n1=p1.next; // have next element 
			ListNode n2=p2.next;
			p1.next=p2;
			if(n1 == null ) { // first one is empty, break the loop as we have already assign to p2
				break;
			}
			p2.next=n1;
			
			p1=n1;
			p2=n2;
		}
		return l1;
	}
	
	
	// Let store the all the node address in an array or List
	// it is kind of brute force solution 
	public static void reorderList(ListNode head) {
        List<ListNode> listNodes= new ArrayList<ListNode>();
        
        // populate the array.
        
        ListNode ptr=head;
        while(ptr != null) {
        	listNodes.add(ptr);
        	ptr= ptr.next;
        }
        // have some base case check
        if(listNodes.size() <=1) {
        	return;// do nothing
        }
        int left=0;
        int right=listNodes.size()-1;
        
        while(left <=right) {
        	listNodes.get(left).next=listNodes.get(right);
        	listNodes.get(right).next=listNodes.get(left+1);
        	left++;
        	right--;
        }
        listNodes.get(right+1).next=null;
    }
	
	public static void main(String[] args) {
		ListNode head=Util.createListNode(1, 4);
		Util.printListNode(head);
		System.out.println();
		reorderList(head);
		Util.printListNode(head);
		
		ListNode head1=Util.createListNode(1, 4);
		ListNode head2=Util.createListNode(7, 8);
		ListNode merged=merge(head1, head2);
		Util.printListNode(merged);

	}
	
	
	


}
