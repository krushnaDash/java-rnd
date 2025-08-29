package com.krushna.Java_rnd.neetcode.binarysearch;


/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 */
public class SearchinRotatedSortedArray {

	public static int search(int[] nums, int target) {
		int left=0;
		int right=nums.length-1;
		
	    while(left <= right) {
	    	int mid=(left+right)/2;
	    	if(nums[mid] == target) {
	    		return mid;
	    	// find sorted array , is it left or right
	    	}else if (nums[mid]>= nums[left] ) { // left array is sorted do a binary search on left array
	    		
	    		int index= binarySearch(left, mid-1, nums, target);
	    		if(index != -1)
	    			return index;
	    		// go to right part
	    		left=mid+1;
	    	}else {
	    		int index= binarySearch(mid+1, right, nums, target);
	    		if(index != -1)
	    			return index;
	    		// go to left part
	    		right=mid-1;
	    		
	    	}
	    	
	    }
	    return -1;

	}
	
	public static  int binarySearch(int low, int high, int[] nums, int target) {
		if(low > high)
			return -1;
		int mid=(low+high)/2;
		
		if(target== nums[mid]) {
			return mid;
		}else if (target > nums[mid]) {
			return binarySearch(mid+1,  high, nums,  target);
		}else {
			return binarySearch(low,  mid-1, nums,  target);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println( search(new int [] {4,5,6,7,0,1,2}, 0));
		System.out.println( search(new int [] {4,5,6,7,0,1,2}, 3));
		System.out.println( search(new int [] {1}, 0));
	}

}
