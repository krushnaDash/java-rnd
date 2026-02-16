package com.krushna.Java_rnd.neetcode.slidingWindow;

import java.util.LinkedHashSet;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

 

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

 */
public class ContainsDuplicateII {

	
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		// window size is K here
		// Lets use Linked Hash set to remove the first element if window size increase
		LinkedHashSet<Integer> numSet = new LinkedHashSet<Integer>();

		for (int i = 0; i < nums.length; ++i) {
			// window size
			if (i > k && !numSet.isEmpty()) {
				numSet.removeFirst();
			}
			if (!numSet.add(nums[i])) {
				return true;
			}
		}
		return false;
	}

}
