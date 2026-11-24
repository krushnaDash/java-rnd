package com.krushna.Java_rnd.neetcode.math_geometry;

/**
 * you are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 *
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        for(int i= digits.length-1; i>=0; --i){
            digits[i]=digits[i]+1;
            if(digits[i]<10){
                break;
            }else{
                digits[i]=0;
            }
        }
        if(digits[0]==0){
            int[] newDigits=new int[digits.length+1];
            newDigits[0]=1;// remaning number are by default 0
            return  newDigits;
        }else {
            return  digits;
        }
    }

}
