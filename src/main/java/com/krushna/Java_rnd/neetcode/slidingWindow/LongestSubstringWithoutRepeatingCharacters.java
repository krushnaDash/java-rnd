package com.krushna.Java_rnd.neetcode.slidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * 
 * Given a string s, find the length of the longest substring without duplicate
 * characters.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * Example 1:
 * 
 * Input: s = "zxyzxyz"
 * 
 * Output: 3
 * 
 * Explanation: The string "xyz" is the longest without duplicate characters.
 * 
 * Example 2:
 * 
 * Input: s = "xxxx"
 * 
 * Output: 1
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		// base case
		if(s.length()<= 1)
			return s.length();
		int maxSubString=1;
		
		char[] chars= s.toCharArray();
		int p1=0;
		int p2=1;
		
		while(p2 < chars.length) {
			int currentSize=1;
			// go back to till p1, check if new char is matching or not
			for(int i=p2-1; i>=p1; --i) {
				if(chars[p2] != chars[i]) {
					currentSize++;
				}else {
					// if do not match, then move the first pointer p1 , to the position the last mismatch
					p1=i+1;
					break;
				}
				
			}
			maxSubString=Math.max(currentSize, maxSubString);
			p2++;
		}
		return maxSubString;
	}

	// this is using hash set
	public int lengthOfLongestSubstringv2(String s) {
		int l=0, r=0;
		Set<Character> chars= new HashSet<>();
		int maxSize=0;
		while ( r< s.length()){
			Character c= s.charAt(r);
			if(chars.contains(c)) {
				// move the L and remove the chars, till its unique again
				while (l < r && chars.contains(c)){
					chars.remove(s.charAt(l));
					++l;
				}
			}
			// add the current char
			chars.add(c);
			maxSize=Math.max(maxSize, chars.size());
			++r;
		}
		return maxSize;
	}

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("zxyzxyz"));
		System.out.println(lengthOfLongestSubstring("xxxx"));
		System.out.println(lengthOfLongestSubstring("dvdf"));
		

	}

}
