package com.krushna.Java_rnd.neetcode.slidingWindow;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 * 
 * You are given a string s and an integer k. You can choose any character of
 * the string and change it to any other uppercase English character. You can
 * perform this operation at most k times.
 * 
 * Return the length of the longest substring containing the same letter you can
 * get after performing the above operations.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "ABAB", k = 2 Output: 4 Explanation: Replace the two 'A's with two
 * 'B's or vice versa.
 * 
 * Example 2:
 * 
 * Input: s = "AABABBA", k = 1 Output: 4 Explanation: Replace the one 'A' in the
 * middle with 'B' and form "AABBBBA". The substring "BBBB" has the longest
 * repeating letters, which is 4. There may exists other ways to achieve this
 * answer too.
 * 
 */
public class LongestRepeatingCharacterReplacement {

	//refer this
	public static int characterReplacement(String s, int k) {
		// base case
		if(s.length() <= 1)
			return s.length();
		
		if(s.length() <= k) {
			return s.length();
		}
		

		int maxCharCount = Integer.MIN_VALUE;

		int l = 0;
		int r = 0;
		int maxSubStringSize = Integer.MIN_VALUE;

		// Use and array to store the count

		int[] countArray = new int[26];

		while (r < s.length()) {
			char cc = s.charAt(r);
			countArray[cc - 'A'] = countArray[cc - 'A'] + 1;

			maxCharCount = Math.max(maxCharCount, countArray[cc - 'A']);

			// check did we reached to replace K character and adding next char is not going
			// move till it is not valid any more
			// we no need to do +1, as we need consider from previous char
			
			if (k < (r+1 - l) - maxCharCount) {
              countArray[s.charAt(l) - 'A'] --; // decrease the count
              ++l; // move the left pointer we need to decriment the char
              // we no need to decrease the maxCount since we are looking for max Substring, if max count needs to decrease
              // that substring must not be max one, we should only increase.
              
			}
			
			maxSubStringSize=Math.max(maxSubStringSize, r+1 - l);
			
			++r;

		}
		return maxSubStringSize;
	}
	
	
	 public static int characterReplacement2(String s, int k) {
	        HashMap<Character, Integer> count = new HashMap<>();
	        int res = 0;

	        int l = 0, maxf = 0;
	        for (int r = 0; r < s.length(); r++) {
	            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
	            maxf = Math.max(maxf, count.get(s.charAt(r)));

	            if ((r - l + 1) - maxf > k) {
	                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
	                l++;
	            }
	            res = Math.max(res, r - l + 1);
	        }

	        return res;
	    }
	
	
	public static void main(String[] args) {
		System.out.println(characterReplacement("ABAB",2));
		System.out.println(characterReplacement("AABABBA",1));
		
		System.out.println(characterReplacement("ABAB",0));
		
		
	}

}
