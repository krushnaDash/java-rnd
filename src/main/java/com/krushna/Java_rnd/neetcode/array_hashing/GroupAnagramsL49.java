package com.krushna.Java_rnd.neetcode.array_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of strings strs, group the
 * 
 * together. You can return the answer in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * 
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Explanation:
 * 
 * There is no string in strs that can be rearranged to form "bat". The strings
 * "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to
 * form each other.
 * 
 * Example 2:
 * 
 * Input: strs = [""]
 * 
 * Output: [[""]]
 * 
 * Example 3:
 * 
 * Input: strs = ["a"]
 * 
 * Output: [["a"]]
 * 
 * 
 */
public class GroupAnagramsL49 {

	public List<List<String>> groupAnagramsOptimise(String[] strs) {
		// create a charcter frequency Array and keep this as key and store in map.
		Map<String, List<String>> anagramMap = new HashMap<>();
		for (String s : strs) {
			anagramMap.computeIfAbsent(getCharcterFrequencyArray(s), ignore -> new ArrayList<String>()).add(s);
		}
		return new ArrayList<List<String>>(anagramMap.values());
	}

	public String getCharcterFrequencyArray(String data) {
		int[] charFrequncyArray = new int[26];
		for (int i = 0; i < data.toCharArray().length; ++i) {
			charFrequncyArray[data.charAt(i) - 'a']++;
		}
		return Arrays.toString(charFrequncyArray);
	}

	public List<List<String>> groupAnagrams(String[] strs) {

		List<List<String>> reuslt = new ArrayList<List<String>>();

		Set<Integer> processIndex = new HashSet<Integer>();

		for (int i = 0; i < strs.length; ++i) {
			List<String> value = new ArrayList<String>();
			if (processIndex.contains(i))
				continue;
			value.add(strs[i]);
			processIndex.add(i);

			for (int j = i + 1; j < strs.length; ++j) {

				if (processIndex.contains(j))
					continue;

				if (isAnagram(strs[i], strs[j])) {
					value.add(strs[j]);
					processIndex.add(j);
				}
			}
			reuslt.add(value);
		}

		return reuslt;
	}

	public boolean isAnagram(String s, String t) {

		// base case
		if (s.length() != t.length())
			return false;

		// lets use any array of size 26 and store the frequency for the char
		// we can incriment for one and decriment for other so the net should be zero.

		int[] charFrequency = new int[26];

		for (int i = 0; i < s.length(); ++i) {
			charFrequency[s.charAt(i) - 'a']++;
			charFrequency[t.charAt(i) - 'a']--;
		}
		for (int i : charFrequency) {
			if (i != 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] strs = { "bdddddddddd", "bbbbbbbbbbc" };
		GroupAnagramsL49 anagramsL49 = new GroupAnagramsL49();
		System.out.println(anagramsL49.groupAnagramsOptimise(strs));
	}

}
