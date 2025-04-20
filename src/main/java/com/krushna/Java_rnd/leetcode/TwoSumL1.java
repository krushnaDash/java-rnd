package com.krushna.Java_rnd.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1
 * 
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,7,11,15], target = 9 Output: [0,1] Explanation: Because
 * nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * Example 2:
 * 
 * Input: nums = [3,2,4], target = 6 Output: [1,2]
 * 
 * Example 3:
 * 
 * Input: nums = [3,3], target = 6 Output: [0,1]
 * 
 */

public class TwoSumL1 {
	public static void main(String[] args) {
		int[] result=twoSum(new int[] {3,2,4}, 6);
		
	}
	/**
	 * Can be solved with two pointer also
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// we will store the number as key and their index as value
		// if if we have number in the key which value same as , target-nums[i], then return the index.
		// here the solution is a[i]+a[j]=target
		// this can be written as a[j]=target-a[i]
		
		for(int i=0; i < nums.length; ++ i) {
			// we have an number in map which is equal to target-a[i]
			if(map.containsKey(target-nums[i])) {
				return new int[] {map.get(target-nums[i]), i};
			}else {
				map.put(nums[i], i);
			}
		}
		return null;
		
    }
}
         