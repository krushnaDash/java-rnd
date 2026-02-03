package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/ 
 * 
 * Given a collection of
 * candidate numbers (candidates) and a target number (target), find all unique
 * combinations in candidates where the candidate numbers sum to target.
 * 
 * Each number in candidates may only be used once in the combination.
 * 
 * Note: The solution set must not contain duplicate combinations.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: candidates = [10,1,2,7,6,1,5], target = 8 Output: [ [1,1,6], [1,2,5],
 * [1,7], [2,6] ]
 * 
 * Example 2:
 * 
 * Input: candidates = [2,5,2,1,2], target = 5 Output: [ [1,2,2], [5] ]
 * 
 * 
 */
public class CombinationSumII {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> currentList= new ArrayList<Integer>();
		
		Arrays.sort(candidates);
		
		dfs(candidates, target, 0, currentList, result, 0);
		return result;
	}
	
	// Lets solve with DFS first
	
		public void dfs(int[] candidates, int target, int currentSum, List<Integer> currentList,
				List<List<Integer>> result,int i) {
			// base case
			if(currentSum == target) {
				// we found a match add that to our result and return
				// we need to add the copy
				result.add(new ArrayList<Integer>(currentList));
				return ;
			}
			// other cases
			
			if(currentSum > target || i >= candidates.length) {
				return;
			}
			// add the current element and call the DFS
			currentList.add(candidates[i]);
			dfs(candidates, target, currentSum+candidates[i], currentList, result, i+1);
			// remove the appended element 
			currentList.removeLast();
			// skip the elements if it is same  element and call the DFS
			while( i< candidates.length-1 && candidates[i] == candidates[i+1])
				++i;
			
			dfs(candidates, target, currentSum, currentList, result, i+1);
		}

}
