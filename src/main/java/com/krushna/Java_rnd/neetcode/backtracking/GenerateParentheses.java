package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/description/
 * 


Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:

Input: n = 1
Output: ["()"]

 * 
 */

public class GenerateParentheses {
	
	public static  List<String> generateParenthesis(int n){
		List<String> result= new ArrayList<>();
		
		generateParenthesisByDFS(n, 0, 0, "", result);
		return result;
	}
	
	public static void generateParenthesisByDFS(int n, int currentOpen, int currentClose, String currentResult, List<String> result){
		// base case
		if(currentOpen==n && currentOpen==currentClose) {
			// we can not have any more open bracket, now add the remaining pending close bracket.
			result.add(currentResult);
		}
		// choices
		if(currentOpen < n) {
			currentResult=currentResult+"(";
			//currentOpen=currentOpen+1;
			generateParenthesisByDFS(n, currentOpen+1, currentClose, currentResult, result);
			// delete the added char
			currentResult=currentResult.substring(0,currentResult.length()-1);
		}
		if(currentClose < currentOpen) {
			currentResult=currentResult+")";
			//currentClose=currentClose+1;
			generateParenthesisByDFS(n, currentOpen, currentClose+1, currentResult, result);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(generateParenthesis(3));
	}

}
