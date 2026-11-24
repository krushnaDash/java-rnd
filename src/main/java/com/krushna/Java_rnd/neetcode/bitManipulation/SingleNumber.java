package com.krushna.Java_rnd.neetcode.bitManipulation;

public class SingleNumber {

    /**
     * Key properties of XOR:
     *
     *     a ^ a = 0 (a number XORed with itself cancels out)
     *     a ^ 0 = a (XOR with 0 keeps the number unchanged)
     *     XOR is commutative and associative, so order does not matter
     *
     * Because of these properties:
     *
     *     all numbers that appear twice will cancel each other out
     *     the number that appears once will remain
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int num=nums[0];
        for(int i=1; i < nums.length; ++i){
            num = num ^ nums[i]; // do XOR operation, when both num same it will be 0 else the first nume
        }
        return  num;
    }

}
