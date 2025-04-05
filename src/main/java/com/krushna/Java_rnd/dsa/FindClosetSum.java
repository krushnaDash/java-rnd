package com.krushna.Java_rnd.dsa;

/**
 * Given an array of signed integers and a number “n”, find sum of any 3
 * elements from array whose sum is closest to n.
 */

public class FindClosetSum {

	public static void main(String[] args) {
		System.out.println(getClosetSum(new int[] {1,2,3,4,5}, 7)); 
		System.out.println(getClosetSum(new int[] {1,2,3,4,5}, 10));
		System.out.println(getClosetSum(new int[] {1,2,3,4,5}, 0));
		System.out.println(getClosetSum(new int[] {1,2,3,4,5}, 1));
		System.out.println(getClosetSum(new int[] {-5,-3,-2,1,2,3,4,5}, 0));
		System.out.println(getClosetSum(new int[] {1}, 0));
		
		System.out.println("-----------------");
		
		System.out.println(closestSumBy3(new int[] {1,2,3,4,5}, 7));
		System.out.println(closestSumBy3(new int[] {1,2,3,4,5}, 10));
		System.out.println(closestSumBy3(new int[] {1,2,3,4,5}, 0));
		System.out.println(closestSumBy3(new int[] {1,2,3,4,5}, 1));
		System.out.println(closestSumBy3(new int[] {-5,-3,-2,1,2,3,4,5}, 0));
		System.out.println(closestSumBy3(new int[] {1,2,4,5,6,7}, 12));
			
			
				
		
			
	}

	/**
	 * This method finds the sum of any 3 elements from array whose sum is closest to x
	 * @param number
	 * @param x
	 * @return
	 * we can consider 2 elements from left or right result should be same
	 */
	public static int closestSumBy3(int[] number, int x) {
		if(number.length < 2) {
			return number[0];
		}
		int diff=Integer.MAX_VALUE;
		int left=0;
		int right=number.length-1;
		int result=0;
		
		
		while (left+1< right) {
			int sum=number[left]+number[left+1]+number[right];
			if(sum<x) {
				left++;
			}else {
				right--;
			}
			if( Math.abs(x-sum) < diff) {
				diff=Math.abs(x-sum);
				result=sum;
			}
		}
		return result;

	}
	
	/**
	 * Given a sorted array and a number x, find a pair in that array whose sum is closest to x". 
	 * How can we implement this with O(n) time complexity?
	 * @param number
	 * @param x
	 * @return
	 */
	public static int getClosetSum(int number[], int x) {
		if(number.length < 2) {
			return number[0];
		}
		int diff=Integer.MAX_VALUE;
		int left=0;
		int right=number.length-1;
		int result=0;
		
		
		while (left< right) {
			int sum=number[left]+number[right];
			if(sum<x) {
				left++;
			}else {
				right--;
			}
			if( Math.abs(x-sum) < diff) {
				diff=Math.abs(x-sum);
				result=sum;
			}
		}
		return result;
	}
	
	
}
