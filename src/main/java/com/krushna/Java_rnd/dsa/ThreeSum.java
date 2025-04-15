package com.krushna.Java_rnd.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]] where nums[i] + nums[j] + nums[k] == 0, and the indices i, j and k
 * are all distinct.
 * 
 * The output should not contain any duplicate triplets. You may return the
 * output and the triplets in any order.
 * 
 * Example 1:
 * 
 * Input: nums = [-1,0,1,2,-1,-4]
 * 
 * Output: [[-1,-1,2],[-1,0,1]]
 */

public class ThreeSum {

	
	
	public static void main(String[] args) {
		System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));

	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		// First sort the array
		java.util.Arrays.sort(nums);
		
		//two pointer approach
		
		for(int i =0; i < nums.length-2; ++i) {
			
			//since the array is sorted, we just need to check the previous element to skip
			if(i>0 && nums[i] ==nums[i-1])
				continue;
			int j=i+1;
			int k=nums.length-1;
			
			while (j<k) {
				int sums=(nums[j]+nums[k])*-1;
				
				if( sums < nums[i]) {
					j++;
				}else if(sums > nums[i]) {
					--k;
				}else {
					// match collect the elements
					result.add( List.of(nums[i],nums[j],nums[k]));
					++j;
					--k;
				}
			}
		}
		
		return result;
	}
	
}
