package com.krushna.Java_rnd.neetcode.binarysearch;

public class BinarySearch {
	
	 public static int search(int[] nums, int target) {
	        // base case
		 if(nums.length ==1 && nums[0] == target) {
				 return 0;
		 }
		    int left=0;
	        int right=nums.length-1;
	        
	        while(left<=right){
	            int mid=left+(right-left)/2;
	            if( nums[mid]== target)
	             return mid;
	            else if(nums[mid] < target) {
	               left=mid+1;
	            }else {
	                right=mid-1;
	            }
	        }
	        return -1;

	    }
	 
	 public static int searchv2(int[] nums, int target) { 
		 return binarySearch(0, nums.length-1, nums, target);
	 }
	 
	 public static int binarySearch(int low, int high, int[] nums, int target) {
		 if(low> high)
			 return -1;
		 
		 int mid=low+((high-low)/2);
		 if(nums[mid] == target)
			 return mid;
		 else if(nums[mid] < target) {
			return binarySearch( mid+1,  high, nums, target);
		 }else {
			 return binarySearch( low,  mid-1, nums, target);
		 }
		 
	 }
	
	
	public static void main(String[] args) {
		System.out.println( search(new int[] {-1,0,3,5,9,12},9));
		System.out.println( searchv2(new int[] {2,5},5));
	}
	

}
