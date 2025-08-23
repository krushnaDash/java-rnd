package com.krushna.Java_rnd.neetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle in
 * the histogram
 * 

Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.


 * 
 */
public class LargestRectangleInHistogram {

	// we will go through each element and store their height and index in a stack
	// we will continue till top of stack is < then current element
	// as soon as we can't extend the current element, we need to pop and calculate height 
	
	public static int largestRectangleArea(int[] heights) {
			int maxArea=0;
			Deque<int[]> indexHeightStack= new LinkedList<>();
			int lastPopedIndex=0;
			for(int i=0; i < heights.length; ++i) {
				
				if(indexHeightStack.isEmpty() || indexHeightStack.peek()[1] <= heights[i]) {
					indexHeightStack.push(new int [] {i, heights[i]});
				}else {
					// we have pop out and calculate the height, till the height is greater
					while(!indexHeightStack.isEmpty()&& indexHeightStack.peek()[1] > heights[i]) {
						int[] element= indexHeightStack.pop();
						maxArea=  Math.max(maxArea,element[1]* (i- element[0]));
						lastPopedIndex=element[0];
					}
					indexHeightStack.push(new int [] {lastPopedIndex, heights[i]});
					
				}
			}
			// now check the left element from the stack
			for(int[] el: indexHeightStack) {
				maxArea=  Math.max(maxArea, el[1] * (heights.length- el[0]));
			}
			
			return maxArea;
	}
	public static void main(String[] args) {
		System.out.println( largestRectangleArea(new int [] {2,1,5,6,2,3}));
	}

}
