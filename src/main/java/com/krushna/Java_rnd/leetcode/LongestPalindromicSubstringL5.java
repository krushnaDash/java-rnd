package com.krushna.Java_rnd.leetcode;


/**
 * LeetCode5
 * 
 * Given a string s, return the longest palindromic substring
 * in s.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "babad" Output: "bab" Explanation: "aba" is also a valid answer.
 * 
 * Example 2:
 * 
 * Input: s = "cbbd" Output: "bb"
 * 
 */
public class LongestPalindromicSubstringL5 {
	
	public static void main(String[] args) {
		System.out.println(getLongestPalindromic("babad"));
		System.out.println(getLongestPalindromic("cbbd"));
		System.out.println(getLongestPalindromic("a"));
	}

	public static String getLongestPalindromic(String input) {
		// There are two case here, one for the ODD one, where can start from that charcter and expand to left and right
		// still they match
		char[] inputChar=input.toCharArray();
		String longestPalindrom="";
		
		// Odd case
		for(int i=0; i < input.length(); ++i) {
			int left=i;
			int right=i;
			while (left >=0 && right < input.length() && inputChar[left]== inputChar[right] ) {
				--left;
				++right;
			}
			
			String subString= input.substring(left+1, right);
			if(subString.length()>longestPalindrom.length()) {
				longestPalindrom=subString;
			}
		}
		// Even case we needs do i+1
		
		for(int i=0; i < input.length(); ++i) {
			int left=i;
			int right=i+1;
			while (left >=0 && right < input.length() && inputChar[left]== inputChar[right] ) {
				--left;
				++right;
			}
			
			String subString= input.substring(left+1, right);
			if(subString.length()>longestPalindrom.length()) {
				longestPalindrom=subString;
			}
		}
		
		return longestPalindrom;
	}
	
	

}


