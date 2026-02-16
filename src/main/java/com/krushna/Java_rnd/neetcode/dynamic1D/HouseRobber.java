package com.krushna.Java_rnd.neetcode.dynamic1D;

/**
 * you are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

 */

public class HouseRobber {
	
	public int rob(int[] nums) {
		// to decide to rob the house i, we can have formula like rob(i)= max(rob(i-1),
		// rob(i-2)+ nums[i])
		// which means we need rob value of two house before
		// lets compute them and store it
		if (nums != null && nums.length > 1) {
			int rob2 = nums[0];
			int rob1 = Math.max(rob2, nums[1]);

			for (int i = 2; i < nums.length; ++i) {
				int robi = Math.max(rob1, rob2 + nums[i]);
				int temp = rob1;
				rob1 = robi;
				rob2 = temp;
			}
			return rob1;
		} else if(nums.length ==1) {
			return nums[0];
		}else {
			return 0;
		}

	}
}
