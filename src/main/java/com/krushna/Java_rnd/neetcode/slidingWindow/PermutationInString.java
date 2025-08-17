package com.krushna.Java_rnd.neetcode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/permutation-in-string/description/
 * 
 * Given two strings s1 and s2, return true if s2 contains a permutations
 * 
 * of s1, or false otherwise.
 * 
 * In other words, return true if one of s1's permutations is the substring of
 * s2.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s1 = "ab", s2 = "eidbaooo" Output: true Explanation: s2 contains one
 * permutation of s1 ("ba").
 * 
 * Example 2:
 * 
 * Input: s1 = "ab", s2 = "eidboaoo" Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 1 <= s1.length, s2.length <= 104 s1 and s2 consist of lowercase English lette
 */
public class PermutationInString {
	
	
	    // refer this
	    // can we have count instead always checking both array if they math
	    // if all char match we can say 26, and if match 26 we can return true
		public static boolean checkInclusionOptimise(String s1, String s2) {
			// base case
			if (s2.length() < s1.length()) {
				return false;
			}

			// we can use array to have the count

			int[] s1Count = new int[26];
			int[] s2Count = new int[26];
			int matchCount=0;

			// Build the s1Count
			for (int i = 0; i < s1.length(); ++i) {
				s1Count[s1.charAt(i) - 'a']++;
				// we update the s2 for same chars
				s2Count[s2.charAt(i) - 'a']++;
			}
			// do a one time match count
			for(int i=0; i<26; ++i) {
				if(s1Count[i]== s2Count[i])
					matchCount++;
			}
			
			// if they matched we can return from here itself
			if (matchCount ==26)
				return true;

			// have sliding window now

			int l = 0;
			int r = s1.length();

			while (r < s2.length()) {
				if (matchCount ==26)
					return true;
				else {
					// remove the left char and change the count
					s2Count[s2.charAt(l) - 'a']--;
					// check if the new matchCount value
					if(s2Count[s2.charAt(l) - 'a'] == s1Count[s2.charAt(l) - 'a']) {
						matchCount++;
					}// Earlier it was match, now because -1, it is not match
					else if (s2Count[s2.charAt(l) - 'a']+1 == s1Count[s2.charAt(l) - 'a']) {
						matchCount--;
					}
					
					// add the new right char
					s2Count[s2.charAt(r) - 'a']++;
					
					// check if the new matchCount value
					if(s2Count[s2.charAt(r) - 'a'] == s1Count[s2.charAt(r) - 'a']) {
						matchCount++;
					}// Earlier it was match, now because +1, it is not match
					else if (s2Count[s2.charAt(r) - 'a']-1 == s1Count[s2.charAt(r) - 'a']) {
						matchCount--;
					}
					++l;
				}

				// remove the left char

				++r;
			}

			return matchCount==26;
		}

		

	// refer this
	// time complexities is o(26*n) which is o(n)	
	public static boolean checkInclusionCleaner(String s1, String s2) {
		// base case
		if (s2.length() < s1.length()) {
			return false;
		}

		// we can use array to have the count

		int[] s1Count = new int[26];
		int[] s2Count = new int[26];

		// Build the s1Count
		for (int i = 0; i < s1.length(); ++i) {
			s1Count[s1.charAt(i) - 'a']++;
			// we update the s2 for same chars
			s2Count[s2.charAt(i) - 'a']++;
		}
		// if they matched we can return from here itself
		if (matchCount(s1Count, s2Count))
			return true;

		// have sliding window now

		int l = 0;
		int r = s1.length();

		while (r < s2.length()) {
			if (matchCount(s1Count, s2Count))
				return true;
			else {
				// remove the left char and change the count
				s2Count[s2.charAt(l) - 'a']--;
				// add the new right char
				s2Count[s2.charAt(r) - 'a']++;
				++l;
			}

			// remove the left char

			++r;
		}

		return matchCount(s1Count, s2Count);
	}
	
	public static boolean matchCount(int[] s1Count, int[] s2Count) {
		for(int i=0; i< s1Count.length; ++i) {
			if(s1Count[i] !=s2Count[i])
				return false;
		}
		return true;
	}
	
	
	public static  boolean checkInclusion(String s1, String s2) {
		boolean contains=false;
		
		// create s1 count Map
		
		Map<Character, Integer> s1CountMap= new HashMap<>();
		for(char c: s1.toCharArray()) {
			s1CountMap.put(c, s1CountMap.getOrDefault(c, 0)+1);
		}
		
		int l=0;
		int r=0;
		int have=0;
		int need=s1CountMap.size();
		
		// the logic here is , if there is any substring which char count exactly match with char count of 
		// s1 then substring will must match with one of the permutations
		
		// So we will have two map to this using sliding window
		Map<Character, Integer> s2CountMap=new HashMap<>();
		while(r < s2.length()) {
			char lc=s2.charAt(l);
			char rc=s2.charAt(r);
			// if any of left or right char is not in map, that whole substring needs to be reject
			if(!s1CountMap.containsKey(lc) || !s1CountMap.containsKey(rc) ) {
				r++;
				l=r;
				s2CountMap= new HashMap<>();
				have=0;
			}else {
				// check the count map and update the have
				//  we need to ensure exact match, if no exact match we have discard the substring
				int charCount=s2CountMap.getOrDefault(rc, 0);
				s2CountMap.put(rc, charCount+1);
				
				if(charCount+1 == s1CountMap.get(rc)) {
					have++;
				}
				if(have== need) {
					return true;
				}
				// if its more then remove the left char
				if(charCount+1 > s1CountMap.get(rc)) {
					l++;
					s2CountMap.put(lc, s2CountMap.get(lc)-1);
					if(lc!=rc) {
						have--;
					}
				}
				++r;
			}
		}
		return contains;
	}
	
	
	
	public static void main(String[] args) {
		//System.out.println(checkInclusionOptimise("ab", "eidbaooo"));
		System.out.println(checkInclusionOptimise("ab", "eidboaoo"));
		//System.out.println(checkInclusionOptimise("adc", "dcda"));
		//System.out.println(checkInclusionOptimise("hello", "ooolleoooleh"));
	}

}
