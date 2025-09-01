package com.krushna.Java_rnd.neetcode.linkedList;

public class Util {
	public static void printListNode(ListNode head) {
		ListNode ptr=head;
		while(ptr !=null) {
			System.out.print(ptr.val+" -> ");
			ptr=ptr.next;
		}
		System.out.println();
	}
	public static ListNode createListNode(int start, int end) {
		
		ListNode prevNode=null;
		ListNode head=null;
		for(int i=start; i <=end; ++i) {
			
			ListNode listNode= new ListNode(i);
			
			if(prevNode ==null) {
				head=listNode;
				prevNode=listNode;
			}
			prevNode.next=listNode;
			prevNode=listNode;
			
		}
		return head;
		
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
	

}
