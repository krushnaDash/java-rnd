package com.krushna.Java_rnd.neetcode.linkedList;

public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// base case, check
		if (l1 == null || l2 == null)
			return null;
		ListNode root = null;
		ListNode node = root;
		int carry = 0;
		do {
			ListNode newNode = new ListNode();
			int sum = 0;
			if (l2 != null) {
				sum = l1.val + l2.val + carry;
				carry = sum / 10;
				newNode.val = sum % 10;
				l2 = l2.next;
			} else {
				newNode.val = (l1.val + carry) % 10;
				carry = (l1.val + carry) / 10;
			}
			l1 = l1.next;

			if (node == null) {
				node = newNode;
				root = newNode;
			} else {
				node.next = newNode;
				node = newNode;
			}
		} while (l1 != null);

		if (l2 != null) {
			do {
				ListNode newNode = new ListNode();
				newNode.val = (l2.val + carry) % 10;
				carry = (l2.val + carry) / 10;
				l2 = l2.next;
				node.next = newNode;
				node = newNode;
			} while (l2 != null);
		}
		if (carry != 0) {
			node.next = new ListNode(carry);
		}

		return root;
	}

}
