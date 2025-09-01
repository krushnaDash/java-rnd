package com.krushna.Java_rnd.neetcode.linkedList;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/description/ You are
 * given the heads of two sorted linked lists list1 and list2.
 * 
 * Merge the two lists into one sorted list. The list should be made by splicing
 * together the nodes of the first two lists.
 * 
 * Return the head of the merged linked list.
 */
public class MergeTwoSortedLinkedLists {

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

		// base case if one of list is null

		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}

		ListNode newList = null;
		ListNode prevNode = null;
		ListNode newFirst = null;

		while (list1 != null && list2 != null) {

			if (list1.val <= list2.val) {
				newList = list1;
				list1 = list1.next;

			} else {
				newList = list2;
				list2 = list2.next;
			}

			if (prevNode != null) {
				prevNode.next = newList;
			} else {
				newFirst = newList;
			}
			prevNode = newList;
		}
		// check the remaining left over element

		if (list1 != null) {
			prevNode.next = list1;
		}

		if (list2 != null) {
			prevNode.next = list2;
		}

		return newFirst;
	}

}
