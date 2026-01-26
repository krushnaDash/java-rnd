package com.hackerrank.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string of lowercase letters, determine the number of substrings that contain only vowels, and each vowel appears at least once. The vowels are ['a', 'e', 'i', 'o', 'u']. A substring is a contiguous segment of a string.
 
Example 1
Input: s = "aeioaexaaeuiou"
Output: 4
Explanation:
* The segments of vowels include "aeioae"  and "aaeuiou". 
* The first segment does not include all vowels. 
* The second segment contains four substrings with all vowels: "aaeuiou", "aaeuio", "aeuiou", and "aeuio".
Example 2
Input: s = "aaeiouxa"
Output: 2

Explanation:
* The segments of vowels include "aaeiou"  and "a". 
* The first segment contains two substrings with all vowels: "aaeiou", "aeuio".
* The second segment does not include all vowels. 

 */
public class VowelSubstring {
	
	/**
	 * We need to find substring which contains only vowels, so looks for non vowels char and create substring 
	 * and then check 
	 * 
	 */
	public static long vowelsubstring(String s) {
	    
		// lets run two pointer, and we will move the left pointer 
		
		String allVowels="aeiou";
		int i=0;
		int j=0;
		long total=0;
		
		Map<Character, Integer> countMap= new HashMap<Character, Integer>();
		
		while(i < s.length() && j < s.length()) {
			char currentChar= s.charAt(j);
			
			// the current char is not a vowels, then have the first segments
			if( ! allVowels.contains(String.valueOf(currentChar))) {
				String segment=s.substring(i,j);
				System.out.println(segment);
				total+= checkSegment(segment,countMap);
				i=j+1;
				countMap=new HashMap<>();
			}
			countMap.put(currentChar, countMap.getOrDefault(currentChar, 0)+1);
			++j;
		}
		// the last substring when we cover all char
		String segment=s.substring(i,j);
		System.out.println(segment);
		return 2;
	}
	public static long checkSegment(String segment, Map<Character, Integer> countMap) {
		if(segment.length() < 5)
			return 0;
		
		
		// check for all vowels
		
		else return 0;
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		vowelsubstring("aeioaexaaeuiou");
	}

}
