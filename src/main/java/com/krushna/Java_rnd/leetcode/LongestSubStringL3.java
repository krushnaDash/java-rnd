package com.krushna.Java_rnd.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3 
 * 
 * Given a string s, find the length of the longest substring
 * 
 * without duplicate characters.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc", with the
 * length of 3.
 * 
 * Example 2:
 * 
 * Input: s = "bbbbb" Output: 1 Explanation: The answer is "b", with the length
 * of 1.
 * 
 * 
 */
public class LongestSubStringL3 {
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("asjrgapa"));
	}

	/**
	 * This can solve with sliding window were will take two pointer
	 * left=0, and we will slide the right next element
	 * 
	 *  
	 *  
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		if (s.length() < 2) {
			return s.length();
		}
		Set<Character> datset = new HashSet<Character>();
		char[] data = s.toCharArray();

		int max = 0;

		int left = 0;
		for (int right = 0; right < s.length(); ++right) {

			// If we find a duplicate then we have continuously remove element from left till there is no
			// duplicate, we can run a loop for that

			while (datset.contains(data[right])) {
				datset.remove(data[left]);
				++left;
			}

			datset.add(data[right]);

			if (datset.size() > max) {
				max = datset.size();
			}
		}

		return max;
	}

}
