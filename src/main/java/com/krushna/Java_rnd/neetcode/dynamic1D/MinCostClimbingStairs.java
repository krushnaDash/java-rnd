package com.krushna.Java_rnd.neetcode.dynamic1D;

/**
 * 
https://leetcode.com/problems/min-cost-climbing-stairs/description/
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

 

Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.

Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.

 */
public class MinCostClimbingStairs {

	public int minCostClimbingStairs(int[] cost) {
		// 10, 15, 20
		// we will start from left to right and store calculated cost
		int n1=0;
		int n2=0;
		for(int i=cost.length-1; i>=0; --i) {
			// cost at i will be min of (cost[i] + n1,  cost[i] + n2)
			int costi= Math.min(cost[i] +n1, cost[i]+n2); // 20, 15, 25
			int temp=n2;
			n2=costi; // 20, 15, 25
			n1=temp;// 0, 35, 15
		}
		return Math.min(n1, n2);

	}

}
