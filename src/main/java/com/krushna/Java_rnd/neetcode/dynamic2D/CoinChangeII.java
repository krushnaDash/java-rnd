package com.krushna.Java_rnd.neetcode.dynamic2D;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * Example 1:
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class CoinChangeII {

    public int change(int amount, int[] coins) {
        Integer[][] dp = new Integer[amount+1][coins.length];
        //return  DFS(amount,coins,0);
        //return  DFSWithCache(amount,coins,0, dp);
        return  changeBottomUp(amount,coins);
    }

    // can we do bottom up, with space optimisation
    // space complxity is o(amount)
    // Time complexity is o(amount*numCoin)
    public int changeBottomUp(int amount, int[] coins){
        int[] amtRow= new int[amount+1];
        Arrays.sort(coins); // coin has to be sorted, for small to big
        amtRow[0]=1;
        // lets find solution from amount 1 to 5, for each coin from 5 to 1
        for (int ci = coins.length-1; ci >= 0; --ci) {
            // we  can update the array at same place also
            for(int amt=1; amt<=amount; ++amt) {
                int remain = amt - coins[ci];
                if (remain >= 0) {
                    // to avoid out of bound
                    amtRow[amt] = amtRow[remain] + amtRow[amt];
                }
            }
        }
        return  amtRow[amount];
    }


    // can we do bottom up
    public int changeBottomUpv2(int amount, int[] coins){
        int[][] dp= new int[coins.length][amount+1];
        // fill for amount 0 as 1
        for(int i=0; i< coins.length; ++i){
            dp[i][0]=1;
        }
        Arrays.sort(coins); // coin has to be sorted, for small to big
        // lets find solution from amount 1 to 5, for each coin from 5 to 1
        for(int amt=1; amt<=amount; ++amt) {
            for (int ci = coins.length-1; ci >= 0; --ci) {
                int remain = amt - coins[ci];
                if (remain < 0) {
                    dp[ci][amt] = 0;
                } else {
                    // to avoid out of bound
                    dp[ci][amt] = dp[ci][remain] + ((ci + 1) < coins.length ? dp[ci+1][amt] : 0);
                }
            }
        }
        for(int i=0; i< coins.length; ++i){
            System.out.println(Arrays.toString(dp[i]));
        }
        return  dp[0][amount];
    }

    // we will try to solve with DP
    // lets have cache for amount and start
    public int DFSWithCache(int amount, int [] coins, int start, Integer [][] dp){
        int numWays=0;
        if(amount ==0){
            return  1;
        }
        if(amount <0){
            return  0; // In valid Path
        }
        if(dp[amount][start] !=null){
            return  dp[amount][start];
        }
        // to avoid duplicate, lets not use the number once choosen from left to right
        // if coins are [1,2,5], the 2nd branch can't have 1, and 3rd branch will not have 1 & 2
        for(int i=start; i<coins.length; ++i){
            numWays+= DFSWithCache(amount-coins[i], coins, i,dp);
        }
        // cache the result
        dp[amount][start]=numWays;
        return  numWays;
    }


    // we will try to solve with DP
    public int DFS(int amount, int [] coins, int start){
        int numWays=0;
        if(amount ==0){
            return  1;
        }
        if(amount <0){
            return  0; // In valid Path
        }
        // to avoid duplicate, lets not use the number once choosen from left to right
        // if coins are [1,2,5], the 2nd branch can't have 1, and 3rd branch will not have 1 & 2
        for(int i=start; i<coins.length; ++i){
            numWays+= DFS(amount-coins[i], coins, i);
        }
        return  numWays;
    }

    public static void main(String[] args) {
        int [] coins= new int[] {1,2,5};
        CoinChangeII cc= new CoinChangeII();
        System.out.println(cc.change(5, coins));
    }

}
