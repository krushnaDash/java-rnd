package com.krushna.Java_rnd.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers nums containing n + 1 integers where each integer
 * is in the range [1, n] inclusive.
 * 
 * There is only one repeated number in nums, return this repeated number.
 * 
 * You must solve the problem without modifying the array nums and using only
 * constant extra space.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,4,2,2] Output: 2
 * 
 * Example 2:
 * 
 * Input: nums = [3,1,3,4,2] Output: 3
 * 
 * Example 3:
 * 
 * Input: nums = [3,3,3,3,3] Output: 3
 */
public class FindDuplicateL287 {
	public static void main(String[] args) {
		System.out.println(findDuplicate(new int[] {3,3,3,3,3}));
		System.out.println(findDuplicate(new int[] {1,3,4,2,2}));
	}
	
    public static int findDuplicateHashSet(int[] nums) {
       Set<Integer> uniqueNums= new HashSet<Integer>();
       for(int i : nums) {
    	   if(uniqueNums.add(i) == false)
    		   return i;
       }
       return -1;
    }
    
    /**
     * Using two pointer one is fast pointer and another one is slow point
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int fast=0;
        int slow=0;
        // find the intersection
        while(true) {
        	slow=nums[slow];
        	fast=nums[nums[fast]];
        	if(slow==fast)
        		break;
        }
       int slow2=0;
       while(true) {
    	   slow2=nums[slow2];
    	   slow=nums[slow];
    	   if(slow2==slow)
    		   return slow;
       }
        
    }
    
    
}
