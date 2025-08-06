package com.krushna.Java_rnd.leetcode.twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/description/
 * 
 * 
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
 * nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]] Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0. nums[1] + nums[2] + nums[4] =
 * 0 + 1 + (-1) = 0. nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0. The
 * distinct triplets are [-1,0,1] and [-1,-1,2]. Notice that the order of the
 * output and the order of the triplets does not matter.
 * 
 * Example 2:
 * 
 * Input: nums = [0,1,1] Output: [] Explanation: The only possible triplet does
 * not sum up to 0.
 * 
 * Example 3:
 * 
 * Input: nums = [0,0,0] Output: [[0,0,0]] Explanation: The only possible
 * triplet sums up to 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 3 <= nums.length <= 3000 -105 <= nums[i] <= 105
 * 
 * 
 * 
 */
public class ThreeSum {
	// nums[i] + nums[j] + nums[k] == 0.
	// which means nums[i]+ nums[j] = - nums[k] - nums[j], -(k+j)
	// i+j+k=0, i+j= -k, i= -k-j, i = -(k+j), k+j = -i
	
	// or we can say for each [i] find the j and K, which sum is minus of -i
	
	// -2, -3, -4 ,, 6, -5 < -6
	
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result= new ArrayList<List<Integer>>();
        // sort the array then we will use two pointer top find j and k
    	Arrays.sort(nums);
    	for(int i=0;i< nums.length; ++i) {
    		// two avoid the duplicate element, i>0 means not first element and the current element not same with
    		// the previous one
    		if(i>0 && nums[i-1]==nums[i]) {
    			continue;
    		}
    		
    		// start the two pointer from i
    		int j=i+1;
    		int k=nums.length-1;
    		while(j< k) {
    			int threeSum=nums[j]+ nums[k]+nums[i];
    			if(threeSum==0) {
    				result.add(Arrays.asList(nums[j], nums[k], nums[i]));
    				++j;--k; // incriment the left pointer and we need continue the same till no duplicate element
    				while(nums[j] == nums[j-1] && j <k) {
    					++j;
    				}
    				
    				
    			}else if(threeSum < 0) { // instead of the -ve of nums[i] we can check with 0 also
    				++j;
    			}else {
    				--k;
    			}
    		}
    	}
    	return result;
    }

}
