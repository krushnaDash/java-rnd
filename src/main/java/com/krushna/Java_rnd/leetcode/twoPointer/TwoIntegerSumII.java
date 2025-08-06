package com.krushna.Java_rnd.leetcode.twoPointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in
 * non-decreasing order, find two numbers such that they add up to a specific
 * target number. Let these two numbers be numbers[index1] and numbers[index2]
 * where 1 <= index1 < index2 <= numbers.length.
 * 
 * Return the indices of the two numbers, index1 and index2, added by one as an
 * integer array [index1, index2] of length 2.
 * 
 * The tests are generated such that there is exactly one solution. You may not
 * use the same element twice.
 * 
 * Your solution must use only constant extra space.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: numbers = [2,7,11,15], target = 9 Output: [1,2] Explanation: The sum
 * of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * 
 * Example 2:
 * 
 * Input: numbers = [2,3,4], target = 6 Output: [1,3] Explanation: The sum of 2
 * and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * 
 * Example 3:
 * 
 * Input: numbers = [-1,0], target = -1 Output: [1,2] Explanation: The sum of -1
 * and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 * 
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 * 
 */

public class TwoIntegerSumII {

	// Since the array is sorted we can use the two pointer to get the value
	public int[] twoSum(int[] numbers, int target) {
		int startPointer=0;
		int lastPointer=numbers.length-1;
		
		while(startPointer < lastPointer) {
			if(numbers[startPointer] + numbers[lastPointer] == target) {
				// we did plus 1, as we need start the index from 1 as per the question
				return new int[]{startPointer+1, lastPointer+1};
			}else if(numbers[startPointer] + numbers[lastPointer] < target) {
				startPointer++;
			}else {
				lastPointer--;
			}
		}
		return null;
	}
	
	// two sum if the array is not sorted.
	
	public int[] twoSumByHashMap(int[] numbers, int target) {
		// number index map
		Map<Integer, Integer> map= new HashMap<>();
		
		// x+y = target, y= target-x, we will look the number traget-x in the map
		
		for( int i=0; i< numbers.length; ++ i) {
			if( map.get( target- numbers[i]) != null ) {
				return new int[] {map.get(target- numbers[i])+1, i+1 };
			}
			map.put(numbers[i], i);
		}
		return null;
	}

}
