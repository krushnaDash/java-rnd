package com.krushna.Java_rnd.neetcode.slidingWindow;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ 
 * you are given
 * an integer array prices where prices[i] is the price of NeetCoin on the ith
 * day.
 * 
 * You may choose a single day to buy one NeetCoin and choose a different day in
 * the future to sell it.
 * 
 * Return the maximum profit you can achieve. You may choose to not make any
 * transactions, in which case the profit would be 0.
 * 
 * Example 1:
 * 
 * Input: prices = [10,1,5,6,7,1]
 * 
 * Output: 6
 * 
 * Explanation: Buy prices[1] and sell prices[4], profit = 7 - 1 = 6.
 * 
 * Example 2:
 * 
 * Input: prices = [10,8,7,5,2]
 * 
 * Output: 0
 * 
 */
public class BestTimetoBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		// base case
		if(prices.length <2) {
			return 0;
		}
		int maxProfit=0;
		int p1=0;
		int p2=1;
		while(p2 < prices.length) {
			maxProfit=Math.max(maxProfit, prices[p2]- prices[p1]);
			if(prices[p1] > prices[p2]) {
				p1=p2;
			}
			p2++;
		}
		
		return maxProfit;
	}
	
	public static void main(String[] args) {
		System.out.println(maxProfit(new int [] {10,1,5,6,7,1}));
		System.out.println(maxProfit(new int [] {10,8,7,5,2}));
	}

}
