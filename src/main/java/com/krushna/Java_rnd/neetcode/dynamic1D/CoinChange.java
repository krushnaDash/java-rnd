package com.krushna.Java_rnd.neetcode.dynamic1D;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int [] dp= new int[amount+1];
        Arrays.fill(dp, -1); // -1 means not computed
        dp[0]=0;

        int min=DFSBackTrackWithDP(coins, amount,dp);
        //int min=bottoUPWithDP(coins,amount);
        return  min == Integer.MAX_VALUE ? -1 : min;
    }

    public int bottoUPWithDP(int [] coins, int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        // now lets compute for each amount
        for(int i=1; i<= amount; ++i){
            // we need to try all the coins
            for(int coin: coins){
                // find the min value for 1
                if(coin <= i && dp[i-coin] !=Integer.MAX_VALUE){
                    dp[i]= Math.min(dp[i], 1+dp[i-coin]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1: dp[amount];
    }


    // DFS with storage so that we no need to computed value which are computed already
    public int DFSBackTrackWithDP(int[] coins, int remain,  int[] dp) {
    // Base case: amount reached
        if (remain == 0) {
            return 0; //  no more coin needed we will add +1 for each recursion
        }
        // Base case: negative amount (invalid)
        if (remain < 0) {
            return Integer.MAX_VALUE;
        }
        // check DP memo
        if(dp[remain] != -1) return dp[remain];

        // Try each coin and find minimum
        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {
            int value=DFSBackTrackWithDP(coins, remain - coin,dp);
            int result =  (value==Integer.MAX_VALUE) ? Integer.MAX_VALUE :  1+ value;
            minCoins = Math.min(minCoins, result);
        }
        dp[remain] = minCoins;
        return minCoins;
    }






    // lets solve with DFS backtracking first, with TOP down approach
    public int DFSBackTrack(int[] coins, int remain, int count) {
        // Base case: amount reached
        if (remain == 0) {
            return count;
        }

        // Base case: negative amount (invalid)
        if (remain < 0) {
            return Integer.MAX_VALUE;
        }

        // Try each coin and find minimum
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = DFSBackTrack(coins, remain - coin, count + 1);
            minCoins = Math.min(minCoins, result);
        }

        return minCoins;
    }



    public static void main(String[] args) {
        CoinChange cc= new CoinChange();
        int[] coins={1,3,4,5};
        System.out.println(cc.coinChange(coins,7));
    }
}
