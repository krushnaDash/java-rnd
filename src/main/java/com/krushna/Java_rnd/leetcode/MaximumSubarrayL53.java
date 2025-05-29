package com.krushna.Java_rnd.leetcode;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 * 
 * Given an integer array nums, find the
 * 
 * with the largest sum, and return its sum.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4] Output: 6 Explanation: The subarray
 * [4,-1,2,1] has the largest sum 6.
 * 
 * 
 */
public class MaximumSubarrayL53 {
	
	public static void main(String[] args) {
		int [] nums= {-2,1,-3,4,-1,2,1,-5,4};
		
		System.out.println(maxSubArray(nums));
		System.out.println(maxSubArray( new int[] {-1}));
	}

	public static int maxSubArray(int[] nums) {
		if(nums.length==0)
			return 0;
		
		int maxSum=Integer.MIN_VALUE;
		// lets use the greddy algo, we will check if adding this number make the maxsum less then 0
		// then discard all element and start with zero again.
        int currentSum=0;
		for(int num:nums) {
			 currentSum=currentSum+num;
			 
			if(currentSum > maxSum)
				maxSum=currentSum;
			// if become negative, start from 0 again
			if(currentSum <0) {
				currentSum=0;
			}
		}
		return maxSum;
	}

}
