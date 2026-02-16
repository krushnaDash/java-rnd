package com.krushna.Java_rnd.neetcode.dynamic1D;

/**

https://leetcode.com/problems/house-robber-ii/description/

You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed. 
All houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, 
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, 
return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.


 */
public class HouseRobberII {
	public int rob(int[] nums) {
		// here the only difference is the houses are in circular
		// which means we can rob either 0 or n
		// what if we run the same rob algo two time and one for robbing 0 and one without 0th house and choose max
		if(nums.length ==1) {
			return nums[0];
		}
		if(nums.length ==2) {
			return Math.max(nums[0], nums[1]);
		}
		
		int max=Math.max(robMax(nums, 0, nums.length-1), robMax(nums, 1, nums.length));
		return max;
		
	}
	
	public int robMax(int[] nums, int start, int end) {
		// to decide to rob the house i, we can have formula like rob(i)= max(rob(i-1),
		// rob(i-2)+ nums[i])
		// which means we need rob value of two house before
		// lets compute them and store it
		if (nums != null && end-start > 1) {
			int rob2 = nums[start];
			int rob1 = Math.max(rob2, nums[start+1]);

			for (int i = start+2; i < end; ++i) {
				int robi = Math.max(rob1, rob2 + nums[i]);
				int temp = rob1;
				rob1 = robi;
				rob2 = temp;
			}
			return rob1;
		} else if(end-start ==1) {
			return nums[0];
		}else {
			return 0;
		}

	}

}
