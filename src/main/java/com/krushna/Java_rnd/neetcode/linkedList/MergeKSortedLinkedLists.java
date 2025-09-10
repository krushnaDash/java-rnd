package com.krushna.Java_rnd.neetcode.linkedList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * 
 * you are given an array of k linked-lists lists, each linked-list is sorted in
 * ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: lists = [[1,4,5],[1,3,4],[2,6]] 
 * Output: [1,1,2,3,4,4,5,6] 
 * Explanation:The linked-lists are: [ 1->4->5, 1->3->4, 2->6 ] merging them into one sorted
 * linked list: 1->1->2->3->4->4->5->6
 * 
 * 
 */
public class MergeKSortedLinkedLists {
	
	
	// cleaner easy to understand 3 ms runtime
	public ListNode mergeKLists1(ListNode[] lists) {
		if(lists == null || lists.length==0) {
			return null;
		}
		PriorityQueue<ListNode> minHeap= new PriorityQueue<>( (listNode1, listnode2) -> listNode1.val-listnode2.val);
		for(ListNode node: lists) {
			if(node !=null)
			  minHeap.offer(node);
		}
		if(minHeap.isEmpty())
			return null;
		
		ListNode dummy= new ListNode(-1);
		ListNode tail=dummy;
		while(!minHeap.isEmpty()) {
			ListNode node= minHeap.poll();
			tail.next=node;
			tail=tail.next;
			
			if(node.next !=null)
				minHeap.offer(node.next);
		}
		return dummy.next;
	}
	
	// Simple o(m*n)  
    public ListNode mergeKLists(ListNode[] lists) {
    	if(lists.length ==0) {
    		return null;
    	}
    	ListNode newNode=lists[0];
    	for(int i=1; i < lists.length; ++i) {
    		newNode=merge(newNode, lists[i]);
    	}
      
    	return newNode;    
    }
    
	public ListNode merge(ListNode list1, ListNode list2) {
        // Fast-path base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Use a dummy head to simplify pointer management
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes (one of them is null)
        tail.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
    
    /**
     * Optimized approach using a min-heap (PriorityQueue).
     * Time: O(N log k), Space: O(k), where N is total number of nodes and k is number of lists.
     */
    public ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        if (pq.isEmpty()) return null;

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            tail.next = min;
            tail = min;
            if (min.next != null) pq.offer(min.next);
        }

        return dummy.next;
    }

    /**
     * Optimized approach using divide-and-conquer pairwise merging.
     * Time: O(N log k), Space: O(1) extra (reuses nodes), iterative to avoid recursion stack.
     */
    public ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int n = lists.length;
        int interval = 1;
        while (interval < n) {
            for (int i = 0; i + interval < n; i += interval * 2) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
    

}
