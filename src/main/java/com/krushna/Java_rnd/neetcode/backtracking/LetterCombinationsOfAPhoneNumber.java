package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
Return the answer in any order.
A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 
 */

public class LetterCombinationsOfAPhoneNumber {
	
	public List<String> letterCombinations(String digits) {
		// define a number to letter map
		Map<String, List<Character>> digtCharMap = 
				Map.of("2", List.of('a','b','c'),
						"3", List.of('d','e','f'),
						"4", List.of('g','h','i'),
						"5", List.of('j','k','l'),
						"6", List.of('m','n','o'),
						"7", List.of('p','q','r','s'),
						"8", List.of('t','u','v'),
						"9", List.of('w','x','y','z')
						);
		
		List<String> resList= new ArrayList<String>();
		if(!digits.isEmpty())
			dfs(digits, resList, 0, new StringBuilder(""), digtCharMap);
		return resList;
    }
	
	public void dfs(String digits, List<String> res, int i,StringBuilder currentString, Map<String, List<Character>> digtCharMap) {
		// base case
		if(i>= digits.length()) {
			res.add(new String(currentString));
			return;
		}
		// run the for loop for each char of first letter
		
		for(char c: digtCharMap.get(String.valueOf(digits.charAt(i)))) {
			currentString=currentString.append(c);
			dfs(digits, res, i+1, currentString, digtCharMap);
			// do the clean up
			currentString=currentString.deleteCharAt(currentString.length()-1);
		}
	}
	
	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber aPhoneNumber= new LetterCombinationsOfAPhoneNumber();
		System.out.println(aPhoneNumber.letterCombinations("23"));
		System.out.println(aPhoneNumber.letterCombinations(""));
	}


}
