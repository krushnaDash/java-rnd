package com.krushna.Java_rnd.neetcode.linkedList;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 * 
 * Given the head of a linked list, remove the nth node from the end of the list
 * and return its head.
 * 
 * 1->2->3->4->5, if n=2 the output will be 1->2->3->5 (4 got removed as it is
 * second node from the last)
 * 
 * 
 */

public class RemoveNodeFromEndofLinkedList {

	// The logic here is we will have two pointer, apart by the n
	// which means when the second pointer reach end the first pointer will be at n
	// from the last
	// that means we can remove the node which is point by the first pointer
	// Since this is forward only linked hence we need the previous node to delete
	// the current one
	// To solve we will have two pointer apart by n+1;
	// to to simplify lets have the previous node also
	public ListNode removeNthFromEnd(ListNode head, int n) {
		// base case
		if (head == null) {
			return null;
		}
		if (head.next == null && n >= 1) {
			return null;
		}
		if (n == 0) {
			return head;
		}

		ListNode p1 = head;
		ListNode p2 = head;
		ListNode previous = null;
		int i = 1;

		// move the p2 to n position, we have already done one positions

		while (p2 != null && i < n) {
			p2 = p2.next;
			++i;
		}
		// now move both the pointer till p2 reach null

		while (p2.next != null) {
			previous = p1;
			p1 = p1.next;
			p2 = p2.next;
		}
		// now delete the p1
		if (previous != null)
			previous.next = p1.next;
		else
			head = head.next;// deleting the first element

		return head;
	}

}
