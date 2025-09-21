package com.krushna.Java_rnd.sf;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a string made up of lowercase letters from 'a' to 'z', determine the
 * length of the shortest substring that includes at least one of each distinct
 * letter present in the string.
 * 
 * 
 * 
 * Example
 * 
 * givenString = dabbcabcd
 * 
 * 
 * 
 * The list of all characters in the string is [a, b, c, d].
 * 
 * Two of the substrings that contain all letters are dabbc and abcd.
 * 
 * The shortest substring that contains all of the letters is 4 characters long.
 * Return 4 as the answer.
 * 
 * 
 * Function Description
 * 
 * 
 * 
 * Complete the function shortestSubstring in the editor with the following
 * parameter(s):
 * 
 * string givenString: the string
 * 
 * 
 * 
 * Returns
 * 
 * int: the length of the shortest substring that contains at least one of each
 * character in givenString
 */

public class ShortestSubstringContainingCharacters {

	
	public static int shortestSubstring(String givenString) {
		// Write your code here
		// need and have
		// create distinct set
		Set<Character> chars = givenString.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		Map<Character, Integer> countMap= new HashMap<>();
		int left=0;
		int right=0;
		int need=0;
		int result=Integer.MAX_VALUE;
		
		while(right < givenString.length()) {
			char currnetCahr=givenString.charAt(right);
			// we are interested  on char which are part of set
			if(chars.contains(currnetCahr)) {
				countMap.put(currnetCahr, countMap.getOrDefault(currnetCahr, 0)+1);
				// check if we have all the chars or not and
				if(countMap.get(currnetCahr)==1)
					need++;
				while (need == chars.size() && left <= right) {
					// take the current size first and we will move the left pointer
					result=Math.min(result, right-left+1);
					
					// reduce the count map, if become zero reduce the need
					countMap.put(givenString.charAt(left), countMap.getOrDefault(givenString.charAt(left), 0)-1);
					if(countMap.get(givenString.charAt(left)) ==0) {
						--need;
					}
					left++;
				}
			}
			
			right++;
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(shortestSubstring("dabbcabcd"));
	}
}
