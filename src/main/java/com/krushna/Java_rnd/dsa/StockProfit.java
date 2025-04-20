package com.krushna.Java_rnd.dsa;

/**
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day.
 * 
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you
 * cannot achieve any profit, return 0.
 */
public class StockProfit {

	public static void main(String[] args) {

		int[] prices = { 7, 1, 5, 3, 6, 4 };
		
		System.out.println(maxProfit(prices));
		
		int[] prices2 = { 7, 1, 5, 3, 0, 6, 4 };

	}

	/**
	 * We can have two pointer, left=0, right=1;
	 * now if the prices[left] < prices[right], profit, so we will compare with max and use it 
	 * and then move the right pointer by 1;
	 * 
	 * else, the price is high, so lets move the left pointer by 1 ?? and right by 1
	 * there is bug, to move the left by 1, we should move the left to right now,
	 * consider the prices2 two case
	 *  
	 * 
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;

		int left = 0;
		int right = 1;
		int max = 0;
		
		while (right < prices.length) {
			if (prices[left] < prices[right]) {
				max = Math.max(max, ( prices[right]- prices[left] ));
				right++;
			} else {
				left=right;
				right++;
			}
		}
		return max;
	}
}
