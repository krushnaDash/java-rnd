package com.krushna.Java_rnd.neetcode.slidingWindow;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 * 
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

 */
public class MinimumSizeSubarraySum {

	public int minSubArrayLen(int target, int[] nums) {
    	int curSum = 0;
		int minLength = Integer.MAX_VALUE;
		for (int i = 0, j = 0; i < nums.length && j < nums.length; ++j) {
			curSum += nums[j];
			while (curSum >= target && i<nums.length) {
				minLength = Math.min(minLength, (j - i) + 1);
				// move the i pointer
				curSum = curSum - nums[i];
				++i;
			}
		}
		return minLength==Integer.MAX_VALUE ? 0: minLength;
	}

}
