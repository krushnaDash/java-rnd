package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
https://leetcode.com/problems/subsets-ii/description/

Given an integer array nums that may contain duplicates, return all possible (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:

Input: nums = [0]
Output: [[],[0]]

 */
public class SubsetsII {
	
	
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
        
		List<List<Integer>> res= new ArrayList<List<Integer>>();
		// now lest solve with DFS
		// sort the element to avoid duplicate
		Arrays.sort(nums);
		
		DFS(nums, res, 0, new ArrayList<Integer>());
		return res;
		
    }
	
	public static void DFS(int [] nums, List<List<Integer>> res, int i, List<Integer> currentList) {
		// base case 
		if(i >= nums.length) {
			res.add( new ArrayList<Integer>(currentList));
			return;
		}
		//  we have two choice, include the element and other one is not include.
		
		List<Integer> currentListCopy= new ArrayList<Integer>(currentList);
		currentListCopy.add(nums[i]);
		DFS(nums, res, i+1, currentListCopy);
		
		//do not include the element, move, do not just i+1, we need skip if i+1 is same i
		while(i < nums.length-1 && nums[i] == nums[i+1]) ++i;
		currentListCopy.removeLast();
		DFS(nums, res, i+1, currentListCopy);
		
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.asList(1));
		System.out.println(subsetsWithDup(new int[] {1,2,3}));
	}

}
