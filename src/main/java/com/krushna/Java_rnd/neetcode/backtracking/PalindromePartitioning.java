package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * https://leetcode.com/problems/palindrome-partitioning/description/
 * 
 * given a string s, partition s such that every of substring of  the partition is a palindrome. 
 * Return all possible palindrome partitioning of s.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aab" Output: [["a","a","b"],["aa","b"]]
 * 
 * Example 2:
 * 
 * Input: s = "a" Output: [["a"]]
 * 
 */

public class PalindromePartitioning {

	public List<List<String>> partition(String s) {
		List<List<String>> result= new ArrayList<List<String>>();
		dfs(s, result, new ArrayList<String>(), 0);
		return result;
	}
	
	public void dfs(String s, List<List<String>> result, List<String> currentPart, int i) {
		// base case
		if(i >= s.length()) {
			result.add(new ArrayList<String>(currentPart));
			return;
		}
		// for each possible part run the DFS
		for(int j=i; j< s.length(); ++j) {
			// check the current part is palindrome or not, if yes add them to the list if not ignore
			if(checkPalindrom(s, i,j)) {
				currentPart.add(new String (s.substring(i, j+1)));
				dfs(s, result, currentPart, j+1);
				currentPart.removeLast();
			}
		}
		
	}
	// and substring start, end substring end
	public boolean checkPalindrom(String s, int start, int end) {
		while(start < end) {
			if(s.charAt(start) != s.charAt(end))
				return false;
			start++; end--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		PalindromePartitioning partitioning=  new PalindromePartitioning();
		System.out.println(partitioning.partition("aab"));
	}
	
	
	

}
