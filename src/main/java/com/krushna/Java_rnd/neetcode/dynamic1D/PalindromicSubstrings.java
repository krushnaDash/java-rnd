package com.krushna.Java_rnd.neetcode.dynamic1D;

public class PalindromicSubstrings {
	
	// this is similar to longest palindromic substring,
	// we can use the same approach and count the palindromic substring instead of
	// comparing with longest one

	public int countSubstrings(String s) {
		char[] inputChar = s.toCharArray();
		int count = 0;

		// Odd case
		for (int i = 0; i < s.length(); ++i) {
			int left = i;
			int right = i;
			while (left >= 0 && right < s.length() && inputChar[left] == inputChar[right]) {
				--left;
				++right;
				count++;
			}
		}
		// Even case

		for (int i = 0; i < s.length(); ++i) {
			int left = i;
			int right = i + 1;
			while (left >= 0 && right < s.length() && inputChar[left] == inputChar[right]) {
				--left;
				++right;
				count++;
			}
		}

		return count;
	}
}
