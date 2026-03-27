package com.krushna.Java_rnd.neetcode.dynamic1D;

import java.util.Arrays;

/**
 *
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = nums.length - 2; i >= 0; --i) {
            // check till end
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max( dp[i],1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int [] nums = {0,1,0,3,2,3};
        LongestIncreasingSubsequence lt= new LongestIncreasingSubsequence();
        System.out.println(lt.lengthOfLIS(nums));
    }
}
