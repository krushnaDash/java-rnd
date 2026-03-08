package com.krushna.Java_rnd.neetcode.dynamic1D;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
    int total=(IntStream.of(nums).sum());
    if(total%2 !=0){ // odd number
        return  false;
    }
    int target= total/2;
    Boolean [][] dp = new Boolean[nums.length][target+1]; // 0= i, 1= target
        // return  DFS(nums, target,0);
       //return   DFSWithMemory(nums, target,0, dp);
        return  withDP(nums, target);

    }

    public boolean withDP(int[] nums, int target){
        Set<Integer> number= new HashSet<>();
        // add zero
        number.add(0);
        for(int i=nums.length-1; i>-0; --i ){
            Set<Integer> temp= new HashSet<>();
            for(int n: number){
                if(n+nums[i] == target)
                    return  true;

             temp.add(n+nums[i]);
             temp.add(n);
            }
            number=temp;
        }
        return false;
    }

    public boolean DFSWithMemory(int[] nums, int target, int i, Boolean[][] dp){
        // base case
        if(target ==0){
            return true;
        }
        if(target < 0 ||  i>= nums.length){
            return  false;
        }
        // check DP
        if(dp[i][target] !=null){
            return  dp[i][target];
        }
        boolean check= DFSWithMemory(nums, target-nums[i], i+1,dp) // consider current num
                || DFSWithMemory(nums, target, i+1,dp); // not take current num

        dp[i][target]=check;
        return check;
    }


    public boolean DFS(int[] nums, int target, int i){
        // base case
        if(target ==0){
            return true;
        }
        if(target < 0 ||  i>= nums.length){
            return  false;
        }
        boolean check= DFS(nums, target-nums[i], i+1) // consider current num
                || DFS(nums, target, i+1); // not take current num
        return check;
    }


    public boolean DFSWithMemory(int[] nums, int target, int i, int[][] dp){
        // base case
        if(target ==0){
            return true;
        }
        if(target < 0 ||  i>= nums.length){
            return  false;
        }
        boolean check= DFS(nums, target-nums[i], i+1) // consider current num
                || DFS(nums, target, i+1); // not take current num
        return check;
    }

    public static void main(String[] args) {
        int nums[] ={1,5,11,5};
        PartitionEqualSubsetSum ps= new PartitionEqualSubsetSum();
        System.out.println(ps.canPartition(nums));
        int nums2[] ={1,2,3,4};
        System.out.println(ps.canPartition(nums2));
    }
}
