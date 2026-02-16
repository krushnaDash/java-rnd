package com.krushna.Java_rnd.neetcode.twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique
 * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * 
 * 0 <= a, b, c, d < n a, b, c, and d are distinct. nums[a] + nums[b] + nums[c]
 * + nums[d] == target
 * 
 * You may return the answer in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,0,-1,0,-2,2], target = 0 Output:
 * [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 */
public class FourSum {
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res= new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        // for each d find the a,b, c = target -d
        
        for(int d=0; d < nums.length; ++d) {
        	// to avoid duplicate
        	if(d>0 && nums[d]== nums[d-1]) {
        		continue;
        	}
        	// for each c, find the a and b, a+b= -c-d + target
        	for(int c=d+1; c< nums.length; ++c) {
        		
        		// to avoid duplicate
            	if(c>d+1 && nums[c]== nums[c-1]) {
            		continue;
            	}
        		
        		// start two pointer to find a and b
        		int i=c+1;
        		int j=nums.length-1;
        		
        		while(i<j) {
        			if(i > c+1 && nums[i]==nums[i-1] ) {
        				++i;
        				continue;
        			 }
        			if((long)nums[i]+nums[j] == (long)-nums[c]-nums[d]+target) {
        				// found one pair
        				res.add(List.of(nums[i],nums[j], nums[c],nums[d]));
        				++i;
        			}else if((long)nums[i]+nums[j] > (long)-nums[c]-nums[d]+target) {
        				--j;
        			}else {
        				++i;
        			}
        		}
        		
        	}
        }
        return res;
		
    }
	public static void main(String[] args) {
		int [] a= new int[] {1,0,-1,0,-2,2};
		FourSum f4= new FourSum();
		//System.out.println(f4.fourSum(a, 0));
		int [] a1= new int[] {2,2,2,2,2};
		//System.out.println(f4.fourSum(a1, 8));
		
		int [] a2= new int[] {-2,-1,-1,1,1,2,2};
		System.out.println(f4.fourSum(a2, 0));
		
		
		
		int [] a3= new int[] {1000000000,1000000000,1000000000,1000000000};
		System.out.println(f4.fourSum(a3, -294967296));
		
	}

}
