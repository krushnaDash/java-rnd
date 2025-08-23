package com.krushna.Java_rnd.neetcode.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/daily-temperatures/description/ 
 * 
 * Given an array of integers temperatures represents the daily temperatures, return an array
 * answer such that answer[i] is the number of days you have to wait after the
 * ith day to get a warmer temperature. If there is no future day for which this
 * is possible, keep answer[i] == 0 instead.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: temperatures = [73,74,75,71,69,72,76,73] Output: [1,1,4,2,1,1,0,0]
 * 
 * Example 2:
 * 
 * Input: temperatures = [30,40,50,60] Output: [1,1,1,0]
 * 
 * Example 3:
 * 
 * Input: temperatures = [30,60,90] Output: [1,1,0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 1 <= temperatures.length <= 105 30 <= temperatures[i] <= 100
 * 
 * 
 * 
 */
public class DailyTemperatures {
	
	public static int[] dailyTemperatures(int[] temperatures) {
		int[] result = new int[temperatures.length];
		// in this stack we will store both the number and index
		// the logic here is we will push the first number and start from the 2nd one
		// if the 2nd number is greater then the current one we pop and push the current one and calculate
		// the index diff, else we will only push, the stack will be monotonic decreasing order
		Deque<int[]> numberStack= new LinkedList<>();
		numberStack.push( new int [] {temperatures[0],0});
		for(int i=1; i <temperatures.length; ++i) {
			while(!numberStack.isEmpty() && temperatures[i] > numberStack.peek()[0] ) {
					int [] num=numberStack.pop();
					// for the number we need to calculate the diif
					result[num[1]]=i-num[1];
				}
				// simply push the number no need to do anything
				numberStack.push(new int [] {temperatures[i],i});
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] result=dailyTemperatures(new int[] {73,74,75,71,69,72,76,73});
		System.out.println(Arrays.toString(result));
		//[1,1,4,2,1,1,0,0]
	}

}
