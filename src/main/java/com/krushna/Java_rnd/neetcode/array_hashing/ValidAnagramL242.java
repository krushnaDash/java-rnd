package com.krushna.Java_rnd.neetcode.array_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if t is an
 * 
 * of s, and false otherwise.
 * 
 * An anagram is a string that contains the exact same characters as another
 * string, but the order of the characters can be different.
 * 
 * Example 1:
 * 
 * Input: s = "anagram", t = "nagaram"
 * 
 * Output: true
 * 
 * Example 2:
 * 
 * Input: s = "rat", t = "car"
 * 
 * Output: false
 * 
 */
public class ValidAnagramL242 {

	public boolean isAnagram(String s, String t) {
		// Early return if lengths are different
		if (s.length() != t.length()) {
			return false;
		}

		Map<Character, Integer> sFrequencyMap = getFrequencyMap(s);
		Map<Character, Integer> tFrequencyMap = getFrequencyMap(t);

		// Compare the frequency maps
		for (char c : sFrequencyMap.keySet()) {
			if (!sFrequencyMap.get(c).equals(tFrequencyMap.get(c))) {
				return false;
			}
		}

		return true;
	}

	public Map<Character, Integer> getFrequencyMap(String data) {
		Map<Character, Integer> dataFrequencyMap = new HashMap<>();
		for (char c : data.toCharArray()) {
			dataFrequencyMap.put(c, dataFrequencyMap.getOrDefault(c, 0) + 1);
		}
		return dataFrequencyMap;
	}
	

	// OPTIMIZED METHOD 1: Single HashMap approach (faster than dual HashMap)
	public boolean isAnagramOptimized1(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		Map<Character, Integer> charCount = new HashMap<>();

		// Count characters in first string
		for (char c : s.toCharArray()) {
			charCount.put(c, charCount.getOrDefault(c, 0) + 1);
		}

		// Decrement count for characters in second string
		for (char c : t.toCharArray()) {
			Integer count = charCount.get(c);
			if (count == null || count == 0) {
				return false;
			}
			charCount.put(c, count - 1);
		}

		return true;
	}
	

	// OPTIMIZED METHOD 2: Array-based counting (fastest for lowercase letters)
	public boolean isAnagramOptimized2(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] charCount = new int[26]; // For lowercase a-z

		// this will work only for lowercase letters
		for (int i = 0; i < s.length(); i++) {
			charCount[s.charAt(i) - 'a']++;
			charCount[t.charAt(i) - 'a']--;
		}

		for (int count : charCount) {
			if (count != 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		ValidAnagramL242 solution = new ValidAnagramL242();

		// Test cases
		String s1 = "anagram";
		String t1 = "nagaram";

	}

}
