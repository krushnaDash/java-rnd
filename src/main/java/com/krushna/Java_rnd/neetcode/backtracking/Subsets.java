package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible

(the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:

Input: nums = [0]
Output: [[],[0]]

https://leetcode.com/problems/subsets/description/
 */
public class Subsets {
	
	 
	public static  List<List<Integer>> subsets(int[] nums) {
		//return iteration(nums);

		List<List<Integer>> subLists = new ArrayList<List<Integer>>();

		 dfs(subLists, 0, nums, new ArrayList<Integer>());
		 return subLists;
	}
	
	public static  List<List<Integer>> iteration(int[] nums) {
		List<List<Integer>> subLists= new ArrayList<List<Integer>>();
		// lets add the first node which an empty list
		subLists.add(new ArrayList<Integer>());
		for(int num: nums) {
			// iterate over each subset from the subLists and add the current number
			// since we have two choice add the current one, and keep the existing one
			// add the current element to the list by creating a copy of all the existing element which is will be like
			// not adding the current element
			// take the current size to a variable as new element will get added which will change the size
			int size=subLists.size();
			for(int i=0; i < size; ++i ) {
				// creating a copy of the current element and then apply the decision
				List<Integer> subset= new ArrayList<>(subLists.get(i));
				subset.add(num);
				subLists.add(subset);
			}
		}
		return subLists;
	}
	
	
	// we can use DFS for backtracking and create all the combination 
	public static void dfs(List<List<Integer>> subLists, int index, int[] nums, List<Integer> subset) {
		// i=0
		if(index >= nums.length) {
			// Add the subset to subsets and return
			subLists.add(new ArrayList<>(subset)); //[1,2,3]
			return ;
		}
		
		// there are two choice, either add the current element or not
		// create the copy of the current subset as we are going to modify it
		List<Integer> subsetCopy= new ArrayList<>(subset); //[]
		
		subsetCopy.add(nums[index]);// [1]
		dfs(subLists, index+1, nums, subsetCopy);// 1, [1,2,3]
		
		// do not add the element, so remove currently add element and then call the DFS
		subsetCopy.removeLast(); //[1,2]
		
		dfs(subLists, index+1, nums, subsetCopy);
	}
	
	
	public static void main(String[] args) {
		System.out.println(subsets(new int [] {1,2,3}));
		

	}
	

}
