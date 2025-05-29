package com.krushna.Java_rnd.leetcode;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 * 
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 * 
 * Example 1:
 * 
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6 Explanation: The above
 * elevation map (black section) is represented by array
 * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
 * are being trapped.
 * 
 * Example 2:
 * 
 * Input: height = [4,2,0,3,2,5] Output: 9
 * 
 */
public class TrappingRainWaterL42 {

	/**
	 * We can run two pointer left and right and keep updating leftMax and RightMax, we will move the min pointer
	 * The formula is
	 * min(leftMax, RightMax)-Height[l];
	 * 
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
		// base case
		if(height.length ==0)
			return 0;
		int left=0;
		int right=height.length-1;
		
		int leftMax=height[left];
		int rightMax=height[right];
		
		int sum=0;
		while(left<right) {
			if(leftMax< rightMax) {
				left+=1;
				leftMax=Math.max(leftMax, height[left]);
				sum+=leftMax-height[left];
				
			}else {
				right-=1;
				rightMax=Math.max(rightMax, height[right]);
				sum+=rightMax-height[right];
			}
		}
		
		return sum;
	}

}
