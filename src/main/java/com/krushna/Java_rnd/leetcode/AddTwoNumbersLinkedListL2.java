package com.krushna.Java_rnd.leetcode;

/**
 * LeetCode 2 You are given two non-empty linked lists representing two
 * non-negative integers. The digits are stored in reverse order, and each of
 * their nodes contains a single digit. Add the two numbers and return the sum
 * as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Input: l1 = [2,4,3], 
 *        l2 = [5,6,4] 
 * Output: [7,0,8] 
 * Explanation: 342 + 465 = 807
 * 
 */
public class AddTwoNumbersLinkedListL2 {
	
	public static void main(String[] args) {
		
		int[] num1= new int[] {9,9,9,9,9,9,9};
		int[] num2= new int[] {9,9,9,9};
		
		ListNode root1= fillLinkedList(num1);
		ListNode root2= fillLinkedList(num2);
		
		
		printLinkedList(root1);
		printLinkedList(root2);
		
		ListNode result=addTwoNumbers(root1,root2);
		
		printLinkedList(result);
		
		
		
	}
	
	public static ListNode fillLinkedList(int[] nums) {
		ListNode root= null;
		ListNode node=root;
		
		for(int i: nums) {
			ListNode newNode =new  ListNode(i);
			if(node==null) {
				root=newNode;
				node=newNode;
			}else {
				node.next=newNode;
				node=newNode;
			}
		}
		return root;
	}
	public static void printLinkedList(ListNode root) {
		if(root==null) {
			System.out.println("empty ");
			return;
		}
			
		ListNode node=root;
		do {
			System.out.print(node.val+" ,");
			node=node.next;
		}while(node!=null);
	  System.out.println();	
	}
	
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
		// base case, check
		if (l1 == null || l2 == null)
			return null;
		ListNode root = null;
		ListNode node = root;
		int carry = 0;
		do {
			ListNode newNode= new ListNode();
			int sum = 0;
			if (l2 != null) {
				sum = l1.val + l2.val+carry;
				carry = sum / 10;
				newNode.val = sum % 10;
				l2 = l2.next;
			} else {
				newNode.val = (l1.val + carry) % 10;
				carry = (l1.val + carry) / 10;
			}
			l1 = l1.next;
			
			if (node==null) {
				node=newNode;
				root=newNode;
			}else {
				node.next=newNode;
				node=newNode;
			}
		} while (l1 != null);

		if (l2 != null) {
			do {
				ListNode newNode= new ListNode();
				newNode.val = (l2.val + carry) % 10;
				carry = (l2.val + carry) / 10;
				l2=l2.next;
				node.next=newNode;
				node=newNode;
			} while (l2 != null);
		}
		if(carry!=0) {
			node.next=new ListNode(carry);
		}

		return root;
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
