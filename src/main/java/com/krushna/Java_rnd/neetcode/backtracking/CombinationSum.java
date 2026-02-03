package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/description/
 * 
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 * 
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the of at least one of the chosen numbers is
 * different.
 * 
 * The test cases are generated such that the number of unique combinations that
 * sum up to target is less than 150 combinations for the given input.
 * 
 * 
 */
public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> currentList= new ArrayList<Integer>();
		
		dfs(candidates, target, 0, currentList, result, 0);
		
		return result;
	
	}
	// can we implement a Loop solutions not completed
	// todo complete it 
	
	public List<List<Integer>> combinationSumLoop(int[] candidates, int target) {
	    List<List<Integer>> result = new ArrayList<>();
	    // Each stack element: Object[]{currentList, currentSum, startIndex}
	    java.util.Stack<Object[]> stack = new java.util.Stack<>();
	    stack.push(new Object[]{new ArrayList<Integer>(), 0, 0});

	    while (!stack.isEmpty()) {
	        Object[] state = stack.pop();
	        @SuppressWarnings("unchecked")
	        List<Integer> currentList = (List<Integer>) state[0];
	        int currentSum = (int) state[1];
	        int start = (int) state[2];

	        if (currentSum == target) {
	            result.add(new ArrayList<>(currentList));
	            continue;
	        }
	        if (currentSum > target) {
	            continue;
	        }
	        for (int i = start; i < candidates.length; i++) {
	            List<Integer> nextList = new ArrayList<>(currentList);
	            nextList.add(candidates[i]);
	            stack.push(new Object[]{nextList, currentSum + candidates[i], i});
	        }
	    }
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
		dfs(candidates, target, currentSum+candidates[i], currentList, result, i);
		// remove the appended element 
		currentList.removeLast();
		// skip the current element and call the DFS
		dfs(candidates, target, currentSum, currentList, result, i+1);
	}
	

}