package com.krushna.Java_rnd.leetcode.twoPointer;

/**
 * https://leetcode.com/problems/container-with-most-water/description/ You are
 * given an integer array height of length n. There are n vertical lines drawn
 * such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * 
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 * 
 * Return the maximum amount of water a container can store.
 * 
 * 
 */
public class ContainerWithMostWater {

	
	/**
	 * We will use the two pointer solution, we will keep the first pointer at start and last at end 
	 * and calculate the water, which will be (lastPointer-fisrtPointer)* min(fisrtPointer.Height, lastPointer.Height)
	 * Then we can move the pointer whose height is less.
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
		int firstPointer=0;
		int lastPointer=height.length-1;
		int maxWater=0;
		while(firstPointer < lastPointer) {
			// calculate the water
			int currentCapacity=Math.min(height[firstPointer], height[lastPointer])* (lastPointer-firstPointer);
			if(maxWater < currentCapacity) {
				maxWater=currentCapacity;
			}
			if(height[firstPointer] < height[lastPointer]) {
				++ firstPointer;
			}else {
				--lastPointer;
			}
		}
		return maxWater;
	}
	
	public static void main(String[] args) {
		
		System.out.println(maxArea(new int [] {1,8,6,2,5,4,8,3,7}));
		
	}

}
