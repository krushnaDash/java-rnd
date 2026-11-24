package com.krushna.Java_rnd.neetcode.bitManipulation;

public class MissingNumber {

    /**
     * XOR is commutative and associative (order does not matter)
     * If we XOR:
     *
     *   all numbers from 0 to n
     *   and all numbers present in the array
     *
     * Every number that appears in both places will cancel out, leaving only the missing number.
     *
     * This allows us to find the answer in linear time and constant space, without sorting or extra data structures.
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int res=nums.length;
        for( int i=0; i < nums.length; ++i){
            res= res ^ nums[i];
            res=res ^ i;
        }
        return  res;
    }
   // Math approach, sum will be same, diff is the result
    public int missingNumberV2(int[] nums) {
        int res = nums.length;

        for (int i = 0; i < nums.length; i++) {
            res += i - nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(3 ^ 1 ^ 2 ^ 3 ^ 1 ^ 3);

    }


}
