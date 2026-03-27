package com.krushna.Java_rnd.neetcode.dynamic1D;

/**
 Given an integer array nums, find a that has the largest product, and return the product.
 The test cases are generated so that the answer will fit in a 32-bit integer.
 Note that the product of an array with a single element is the value of that element.

 Example 1:

 Input: nums = [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.
 Example 2:

 Input: nums = [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

 Constraints:

 1 <= nums.length <= 2 * 104
 -10 <= nums[i] <= 10
 The product of any subarray of nums is guaranteed to fit in a 32-bit integer.

 */
public class MaximumProductSubarray {
    // the range is from -10 to 10
    public int maxProduct(int[] nums) {
        int n1= nums[nums.length-1]; // Max
        int maxvalue=n1;
        int n2=n1; // Min
        for(int i=nums.length-2; i>=0; --i){
            int currentMax= Math.max(nums[i]*n2, nums[i]* n1);
            int currentMin= Math.min(nums[i]*n2, nums[i]* n1); // we are stroing the min value also for -ve number
            n1= Math.max(nums[i], currentMax);
            n2= Math.min(nums[i], currentMin);
            maxvalue=Math.max(maxvalue,n1);
        }
        return  maxvalue;
    }

    public static void main(String[] args) {
        int[] nums={-3,-1,-1};
        MaximumProductSubarray mp= new MaximumProductSubarray();
        System.out.println(mp.maxProduct(nums));
        int[] nums2={1,2,-3,4};
        System.out.println(mp.maxProduct(nums2));
        int[] nums3={2,3,-2,4};
        System.out.println(mp.maxProduct(nums3));

        int[] nums4={2,-3,2,-4};
        System.out.println(mp.maxProduct(nums4));
        int [] nums5={-1,-2,-9,-6};
        System.out.println(mp.maxProduct(nums5));

    }
}
