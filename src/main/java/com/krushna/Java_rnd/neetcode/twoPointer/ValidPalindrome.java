package com.krushna.Java_rnd.neetcode.twoPointer;

/**
 * Given a string s, return true if it is a palindrome, otherwise return false.
 * 
 * A palindrome is a string that reads the same forward and backward. It is also
 * case-insensitive and ignores all non-alphanumeric characters.
 * 
 * Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers
 * (0-9).
 * 
 * Example 1:
 * 
 * Input: s = "Was it a car or a cat I saw?"
 * 
 * Output: true
 * 
 * Explanation: After considering only alphanumerical characters we have
 * "wasitacaroracatisaw", which is a palindrome.
 * 
 * Example 2:
 * 
 * Input: s = "tab a cat"
 * 
 * Output: false
 * 
 * Explanation: "tabacat" is not a palindrome.
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 1000 s is made up of only printable ASCII characters.
 * 
 * 
 */

public class ValidPalindrome {
	public static  boolean isPalindrome(String s) {
		s=s.toLowerCase();
		int startPointer=0;
		int lastPointer=s.length()-1;
		
		while(startPointer < lastPointer) {
			// check for checkAlphanumeric
			
			while(!checkAlphanumeric(s.charAt(startPointer)) && startPointer < lastPointer) {
				startPointer++;
			}
			
			while(!checkAlphanumeric(s.charAt(lastPointer)) && lastPointer > startPointer ) {
				lastPointer--;
			}
			
			
			if(s.charAt(startPointer)  != s.charAt(lastPointer))
				return false;
			startPointer++;
			lastPointer--;
		}
		return true;
	}
	
	public static boolean checkAlphanumeric(char c) {
		return
				(c >='A' && c<='Z') ||
				(c>='a' && c<= 'z') ||
				(c>='0' && c<='9');
		
	}
	
	public static void main(String[] args) {
		System.out.println( isPalindrome("Was it a car or a cat I saw?"));
	}

}
