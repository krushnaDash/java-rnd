package com.krushna.Java_rnd.leetcode;

/**
 * LeetCode 4
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 * the median of the two sorted arrays.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,3], nums2 = [2] Output: 2.00000 Explanation: merged array =
 * [1,2,3] and median is 2.
 * 
 * Example 2:
 * 
 * Input: nums1 = [1,2], nums2 = [3,4] Output: 2.50000 Explanation: merged array
 * = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * 
 */

public class MedianOfTwoArrayL4 {
	
	
	public static void main(String[] args) {
		int[] nums1 = {1,3};
		int[] nums2 = {2};
		System.out.println(findMedianSortedArrays(nums1,nums2 ));
		
		int[] nums3 = {1,2};
		int[] nums4 = {3,4};
		System.out.println(findMedianSortedArrays(nums3,nums4 ));
		
	}
	
	
	/**
	 * We need to merge the two sorted arrays and find the median.
	 * we have 3rd array where can keep the merge data, but to have proper time and space complexity
	 * we do not need all the element, we just need two element.
	 * if n is odd, we need the element example, 12345 , n/2
	 * if n is even we need the element example 1234, n/2-1 and n/2
	 * So once we have the element we should use and break the loop  
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		int newSize = nums1.length + nums2.length;
		int midElementIndex = (newSize / 2) - 1;

		int num1 = 0, num2 = 0;

		int j = 0, k = 0;
		for (int i = 0; i <= newSize / 2; ++i) {

			// If both the array has element to compare
			if (j < nums1.length && k < nums2.length) {

				if (nums1[j] < nums2[k]) {
					// choosen number is from nums1
					num2 = nums1[j];
					++j;

				} else {
					num2 = nums2[k];
					++k;
				}

			}
			// just one array has element
			else if (j < nums1.length) {
				num2 = nums1[j];
				++j;
			} else {
				num2 = nums2[k];
				++k;
			}

			if (midElementIndex == i) {
				num1 = num2;
			}

		}
		if (newSize % 2 == 0) {
			return (double) (num1 + num2) / 2;
		} else {
			return num2;
		}

	}

}
