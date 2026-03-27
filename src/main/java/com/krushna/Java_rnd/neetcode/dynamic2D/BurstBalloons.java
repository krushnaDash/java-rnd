package com.krushna.Java_rnd.neetcode.dynamic2D;

import java.util.Arrays;
import java.util.Map;

public class BurstBalloons {

    public int maxCoins(int[] nums) {
        // lets 1 and 1 to left and right of the nums for easy caluclation
        int [] n= new int[nums.length+2];
        n[0]=1;
        n[n.length-1]=1;
        for(int i=0; i < nums.length; ++i){
            n[i+1]=nums[i]; // copy the value
        }
        Integer[][] dp = new Integer[n.length][n.length]; // we use HashMap also
        return  DFS(n,1,n.length-2,dp);
    }
    public int DFS(int[] n, int l, int r, Integer[][] dp){
        // base case if l> r, return 0
        if(l>r){
            return 0;
        }
        // if in DP return the value
        if(dp[l][r] !=null){
            return  dp[l][r];
        }
        // now for each value from L to R, consider this is pop last and create the sub problem and take max
        int coins=0;
        dp[l][r]=0;
        for(int i = l; i <=r; ++i){
            coins= n[l-1]*n[i]*n[r+1]; // l-1 and r+1, as this the last item to pick
            coins+= DFS(n,l, i-1,dp) + DFS(n,i+1,r,dp);
            dp[l][r]= Math.max(dp[l][r], coins);
        }
        return dp[l][r];
    }
}
