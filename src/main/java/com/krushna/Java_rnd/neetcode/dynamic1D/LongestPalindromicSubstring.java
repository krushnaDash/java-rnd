package com.krushna.Java_rnd.neetcode.dynamic1D;

public class LongestPalindromicSubstring {
	
	
	public String longestPalindromeBurtueForce(String input) {
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
		// Even case
		
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
