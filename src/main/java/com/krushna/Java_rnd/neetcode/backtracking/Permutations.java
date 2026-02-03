package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * 
 * Given an array nums of distinct integers, return all the possible . You can
 * return the answer in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3] Output:
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * Example 2:
 * 
 * Input: nums = [0,1] Output: [[0,1],[1,0]]
 * 
 * Example 3:
 * 
 * Input: nums = [1] Output: [[1]]
 * 
 * 
 */

public class Permutations {

	public static List<List<Integer>> permute(int[] nums) {

		return permuteR(nums);

	}

	public static List<List<Integer>> permuteR(int[] nums) {

		// base case
		if (nums.length == 0) {
			return Arrays.asList(new ArrayList<>());
		}
		// create a copy of Arrays excluding the first element
		List<List<Integer>> perms = permuteR(Arrays.copyOfRange(nums, 1, nums.length));
		// now process the result.
		List<List<Integer>> result = new ArrayList<>();

		// now take all the permutation from perms and insert the 0 element at each
		// position

		for (List<Integer> perm : perms) {

			for (int i = 0; i < perm.size() + 1; ++i) {
				List<Integer> permcopy = new ArrayList<>(perm);
				permcopy.add(i, nums[0]);
				result.add(permcopy);
			}

		}

		return result;

	}

	public static void main(String[] args) {
		System.out.println(permute(new int[] { 1, 2, 3 }));
	}

}