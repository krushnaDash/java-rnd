package com.krushna.Java_rnd.neetcode.twoPointer;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/ you are given
 * an array of non-negative integers height which represent an elevation map.
 * Each value height[i] represents the height of a bar, which has a width of 1.
 * 
 * Return the maximum area of water that can be trapped between the bars.
 * 
 * Input: height = [0,2,0,3,1,0,1,3,2,1]
 * 
 * Output: 9
 * 
 * 
 */
public class TrappingRainWater {

	/**
	 * 
	 * @param height
	 * @return
	 * The water holding capacity at position i will be min(leftMax, RightMax) - height[i]
	 * Since it is min, of leftMax and RightMax, we no need to do the full scan mainly from right side
	 * We can take two point left and right and move them.
	 * 
	 */
	public static int trap(int[] height) {
		
		int left=0;
		int totalWater=0;
		int right=height.length-1;
		int leftMax=height[left];
		int rightMax=height[right];
				
		while(left< right) {
			// since we are considering the min and contineu with left till it is small so this will does not matter with really the max right
			if(leftMax <= rightMax) {
				left++;
				leftMax=Math.max(leftMax, height[left]);
				// to avoid the -ve number, if the number is - then take it as 0
				totalWater+= Math.max(0, Math.min(leftMax, rightMax)-height[left]);
			
			}else {
				right--;
				rightMax=Math.max(rightMax, height[right]);
				totalWater+=  Math.max(0,Math.min(leftMax, rightMax)-height[right]);
			}
			
		}
		return totalWater;

	}
	public static void main(String[] args) {
		System.out.println(trap(new int[] {0,2,0,3,1,0,1,3,2,1}));
	}

}
