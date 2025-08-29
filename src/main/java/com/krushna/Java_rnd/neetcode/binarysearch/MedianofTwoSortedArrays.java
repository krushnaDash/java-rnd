package com.krushna.Java_rnd.neetcode.binarysearch;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
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
 * = [1,2,3,4] and median is (2 + 3) / 2 = 2.5
 * 
 * 
 * 
 */
public class MedianofTwoSortedArrays {
	
	
	/**
	 * This one we will write in more optimise version using a binary search to get o(log m+n) time complexity
	 * The logic here is
	 * 1. We need to find the left partition, which will be combination of some element from first and second array
	 * 2. We can have the binary search from smaller array and take the remaining element from the other one
	 * 3. And then do the below comparison to ensure the chosen elements are valid left partition
	 * 4. Rule should be nums1[mid] <= nums2[mid+1] and   nums2[mid] <= nums1[mid]
	 * 5. If the total length is odd then, we can simply pick the min( nums2[mid+1], nums1[mid+1])
	 * 6. If its is even then we need the previous element which can be found by the logic max(nums1[mid], nums2[mid)
	 * Why max for previous element, this is because , the element are sorted in descending order. 
	 * 
	 */
	public static double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
		// lets have some base case to keep the code clean
		
		if(nums1 == null || nums1.length ==0 ) {
			return getMedian(nums2);
		}
		
		if(nums2 == null || nums2.length ==0 ) {
			return getMedian(nums1);
		}
		
		int totlaLength=nums1.length+nums2.length;
		boolean isEven=totlaLength%2==0;
		int elementNeeded=totlaLength/2;
		
		// lets do the binary search on the small Array
		int [] smallArray=nums1.length <= nums2.length ? nums1: nums2;
		int [] bigArray=nums2.length >= nums1.length ? nums2: nums1;
		
		int left=0;
		int right=smallArray.length-1;
		
		// here can have the normal logic of binary search or we can run a infinite loop also as we will always
		// find a match.
		
		while(true) {
			int sMid= (left+right) <0 ? -1:(left+right)/2;
			int bMid= (left+right) <0 ?elementNeeded-1: (elementNeeded-sMid)-2; // simple logic, if need 8 element, sMid is 3, the remaining 5 element will come other
			// why -2, since index start from 0, for each array, we need to minus 1
			// check is this valid partition
			
			int smidPlus1= sMid >=-1 && sMid < smallArray.length-1 ? smallArray[sMid+1] : Integer.MAX_VALUE; // since we need to check <=
			int bigPlus1= bMid >=-1 && bMid < bigArray.length-1 ? bigArray[bMid+1] : Integer.MAX_VALUE;
			
			
			
			// if sMid or bMid <0 then we need to assign - infinite, since we are running for while true
			// we are adding -ve infinite at beginning and +ve infinity at end
			
			int smidValue=sMid >=0 && sMid <= smallArray.length-1 ? smallArray[sMid] : Integer.MIN_VALUE;
			int bmidValue=bMid >=0 && bMid <= bigArray.length-1  ? bigArray[bMid] : Integer.MIN_VALUE;
			
			
			
			// valid left partition we can return the median
			if(smidValue <=bigPlus1 && bmidValue <= smidPlus1) {
				if(isEven) {
					// we need check both
					int fe= Math.max(smidValue, bmidValue);
					int se= Math.min(smidPlus1, bigPlus1);
					return (double)(fe+se)/2;
				}else {
					return Math.min(smidPlus1, bigPlus1);
				}
			}else if (smidValue > bigPlus1) {
				// drop element from small array
				right=sMid-1;
			}else { // need to have more element from small array
				left=sMid+1;
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		//System.out.println(findMedianSortedArraysBS(new int[] {1,2}, new int[] {3,4}));
		System.out.println(findMedianSortedArraysBS(new int[] {1,3}, new int[] {2}));
		System.out.println(findMedianSortedArraysBS(new int[] {}, new int[] {2,3}));
		System.out.println(findMedianSortedArraysBS(new int[] {3,4}, new int[] {}));
	}
	
	
	
	public static double getMedian(int [] e) {
		int mid=e.length/2;
		if(e.length%2 ==0) {
			return (double) (e[mid]+ e[mid-1])/2;
		}else {
			return e[mid];
		}
	}
	
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid=(nums1.length+nums2.length)/2;
        boolean isEven=(nums1.length+nums2.length)%2==0;
        int prev=-1;
        int current=-1;
        int totalCtr=0;
        int i=0,j=0;
        while( i< nums1.length && j<nums2.length) {
        	prev=current;
        	if(nums1[i]< nums2[j]) {
        		current= nums1[i];
        		++i;
        	}else {
        		current= nums2[j];
        		++j;
        	}
        	if(totalCtr == mid) {
        		if(isEven)
        			return (double)(prev+current)/2;
        		else
        			return current;
        	}
        	++totalCtr;
        }
        
        // not return within the loop, that mean not reach to mid
        	int positionToMove=mid-totalCtr;
        	
        	if(i==nums1.length) {
        		if(isEven)
        			return (double) (nums2[j+positionToMove]+ (positionToMove==0? current:nums2[j+positionToMove-1]))/2;
        		else
        			return nums2[j+positionToMove];
        		// move the j
        	}else {
        		if(isEven)
        			return (double) (nums1[i+positionToMove]+ (positionToMove==0? current :nums1[i+positionToMove-1]))/2;
        		else
        			return nums1[i+positionToMove];
        		
        	}
        
    }
	


}
