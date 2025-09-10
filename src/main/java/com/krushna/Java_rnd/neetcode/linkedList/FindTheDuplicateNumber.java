package com.krushna.Java_rnd.neetcode.linkedList;

import java.net.Socket;

/**
 * You are given an array of integers nums containing n + 1 integers. Each
 * integer in nums is in the range [1, n] inclusive.
 * 
 * Every integer appears exactly once, except for one integer which appears two
 * or more times. Return the integer that appears more than once.
 * 
 * 
 */
public class FindTheDuplicateNumber {
	
	
	// This can be solve with slow and fast pointer
	// consider this array represent a linked list which one node creating circular link
	// two problem to solver , circular linked list and then floyd's algo, which says that
	// The distance from slow and fast pointer intersection to duplicate node same from the distance from beginning node
	// 1,2,3,2,2
	public int findDuplicate(int[] nums) {
        int slow=0;
        int fast=0;
		while(true) {
			slow=nums[slow];
			fast=nums[nums[fast]];
        	if(slow == fast)
        		break;
        }
		// now take another slow pointer and move them
		
		int newSlow=0;
		while(true) {
			newSlow=nums[newSlow];
			slow= nums[slow];
			if(newSlow==slow)
				return slow;
		}
		
    }

}
