package com.krushna.Java_rnd.neetcode.linkedList;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 * 
 * Given the head of a linked list, reverse the nodes of the list k at a time,
 * and return the modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes, in
 * the end, should remain as it is.
 * 
 * You may not alter the values in the list's nodes, only nodes themselves may
 * be changed.
 * 
 * Input: head = [1,2,3,4,5], k = 2
 *   Output: [2,1,4,3,5]
 */
public class ReverseNodesNnKGroup {
    
    // Clean, in-place O(1) extra space k-group reversal using a dummy node
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) return head;
        ListNode dummy= new ListNode(-1);
        // we simply visit the linked list and as soon as we have k nodes we will
        // call the reverse by disconnecting and joining back.
        dummy.next=head;
        ListNode groupPrev=dummy;
        ListNode ptr=head;
        int ctr=1;
        while(ptr !=null) {
        	
        	if(ctr==k) {
        		ListNode groupStart=groupPrev.next;
        		ListNode nextGroup=ptr.next;
        		
        		// disconnect
        		ptr.next=null;
        		
        		ListNode reverseHead=reverse(groupStart);
        		
        		// join and move
        		groupPrev.next=reverseHead;
        		groupStart.next=nextGroup;
        		
        		// restore the PTR
        		ptr=groupStart;
        		// advance the groupPrev
        		groupPrev=groupStart;
        		ctr=0;
        	}
        	++ctr;
        	ptr= ptr.next;
        }
        return dummy.next;
        
    }

    // Standard iterative reverse for a whole list (kept for reference/utility)
    public static ListNode reverse(ListNode node) {
        ListNode previous = null;
        ListNode ptr = node;
        while (ptr != null) {
            ListNode temp = ptr;
            ptr = ptr.next;
            temp.next = previous;
            previous = temp;
        }
        return previous;
    }

    public static void main(String[] args) {
        ListNode node = Util.createListNode(1, 5);
        ListNode newNode = reverseKGroup(node, 2);
        Util.printListNode(newNode);
    }
    

}
