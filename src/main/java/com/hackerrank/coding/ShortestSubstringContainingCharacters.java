package com.hackerrank.coding;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string made up of lowercase letters from a to z, determine the length
 * of the shortest substring that includes at least one of each distinct letter
 * in the string.
 * 
 * 
 * 
 * Example 1 Input: s = "dabbcabcd"
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * All characters in the string: [a, b, c, d] Two of the substrings that contain
 * all letters are "dabbc" and "abcd". "abcd" is the shorter of the two.
 * 
 * 
 * Example 2 Input: s = "asdfkjeghfalawefhaef"
 * 
 * Output: 13
 * 
 * Explanation:
 * 
 * All characters in the string: [a, d, e, f, g, h, j, k, l, s, w] The shortest
 * substring that contains all letters is "sdfkjeghfalaw".
 * 
 */


public class ShortestSubstringContainingCharacters {
	
	
	    /**
	     * Optimise version 
	     * @param s
	     * @return
	     */
		public static int shortestSubstringv2(String s) {
		    // base case
			
			if(s==null || s.length()==0)
				return -1;
			
			if(s.length()==1)
				return 1;
			
			// create the unique char set
			Set<Character> charSet= new HashSet<Character>();
			for(char c: s.toCharArray()) {
				charSet.add(c);
			}
			// start the sliding window and check each substring.
			int min=0;
			
			// todo
			return min;
			
			
		}
		
	
	//basic approach pass all the test cases
	public static int shortestSubstring(String s) {
	    // base case
		
		if(s==null || s.length()==0)
			return -1;
		
		if(s.length()==1)
			return 1;
		
		// create the unique char set
		Set<Character> charSet= new HashSet<Character>();
		for(char c: s.toCharArray()) {
			charSet.add(c);
		}
		// start the sliding window and check each substring.
		int min=0;
		
		int p=0; 
		int q=1;
		while(p < s.length() && q< s.length()) {
			String subString=s.substring(p,q+1);
			while(checkAllChars(charSet,subString)) {
				min=Math.min(min, subString.length());
				// now drop the char from left check if it still valid
				++p;
				subString=s.substring(p,q+1);
			}
			++q;
		}
		
		return min;
		
		
	}
	
	public static boolean checkAllChars(Set<Character> charSet, String subString) {
		for(char c: charSet) {
			if(!subString.contains(String.valueOf(c)))
					return false;
		}
		return true;
	}

}
