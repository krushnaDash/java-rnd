package com.krushna.Java_rnd.leetcode;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If
 * reversing x causes the value to go outside the signed 32-bit integer range
 * [-231, 231 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed or
 * unsigned).
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: x = 123 Output: 321
 * 
 */
public class ReverseIntegerL7 {

	public static void main(String[] args) {
		
		
	}
	
	public static int reverse(int x) {
		int reverseNum=0;
		while(x !=0) {
			int digit=x%10;
			x=x/10;
			if(reverseNum > Integer.MAX_VALUE/10 || (reverseNum== Integer.MAX_VALUE  && digit > Integer.MAX_VALUE%10))
				return 0;
			if(reverseNum < Integer.MIN_VALUE/10 || (reverseNum== Integer.MAX_VALUE  && digit < Integer.MAX_VALUE%10))
				return 0;
			
			reverseNum=reverseNum*10+digit;
		}
		
		return reverseNum;
	}

}
