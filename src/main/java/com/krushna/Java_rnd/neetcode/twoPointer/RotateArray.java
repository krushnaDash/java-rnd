package com.krushna.Java_rnd.neetcode.twoPointer;

import java.sql.Array;
import java.util.Arrays;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
            while (k >0){
                roateOne(nums);
                --k;
            }
    }

    public void roateOne(int [] nums){
        int p1=0;
        int p2=nums.length-1;
        int num1=nums[p1];
        nums[p1]=nums[p2];
        for(int i=1; i < nums.length; ++i ){
            int temp=nums[i];
            nums[i]= num1;
            num1=temp;
        }
    }

    //better approach  can we make space as o(1), time as o(n)
    // by doing two reversal,
    public void rotateFinal(int[] nums, int k) {
        // this will make sure if k > nums.lenght, we will shift only reminder
        k= k %nums.length;
        // first reverse with two pointer
        reverElement(nums,0,nums.length-1);
        // second reverse of the kth element from beginning and rest
        reverElement(nums,0,k-1);
        // second part
        reverElement(nums,k,nums.length-1);
    }
    public void reverElement(int[] nums, int l, int r){
        while (l<r){
            int t=nums[l];
            nums[l]=nums[r];
            nums[r]=t;
            ++l;--r;
        }
    }


    // another clean approach using a new array shift the element by k
    // Time complexity is 0(n) and space also o(n)
    // better approach ???, can we make space as o(1)
    public void rotatev1(int[] nums, int k) {
       int[] temps= new int[nums.length];
       System.arraycopy(nums,0,temps,0, nums.length);
       for(int i =0; i< temps.length; ++i){
           // copy to right index, by adding the k, use % for circular
           int index= (i+k)%nums.length;
           nums[index]=temps[i];
        }
    }


    public static void main(String[] args) {
        RotateArray ra= new RotateArray();
        int [] nums= {1,2,3,4,5,6,7};
        ra.rotate(nums,3);
        System.out.println(Arrays.toString(nums));
    }


}
