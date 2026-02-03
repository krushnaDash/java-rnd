package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/n-queens/description/
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You
 * may return the answer in any order.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space,
 * respectively.
 * 
 *Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

Example 2:

Input: n = 1
Output: [["Q"]]

 */

public class NQueens {
	
	Set<Integer> colSet= new HashSet<Integer>();
	Set<Integer> postiveDig= new HashSet<Integer>();
	Set<Integer> negativeDig= new HashSet<Integer>();

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result= new ArrayList<List<String>>();
		char[][] boards= new char[n][n];
		// fill the boards with .
		
		for(int i=0; i < n; ++i ) {
			Arrays.fill(boards[i], '.');
		}
		solveWithBackTrackingDFC(n, 0, result, boards);
		return result; 
	}
	
	public void solveWithBackTrackingDFC(int n, int r, List<List<String>> result, char[][] boards) {
		// base case, all the row is cover
		if(r>=n) {
			// create copy of the current List
			// process the boards to create the List<String> in the required format
			List<String> resultString= new ArrayList<String>();
			for(char[] chars : boards) {
				resultString.add(new String(chars));
			}
			result.add(resultString);
		}
		// for each cols check
		
		for(int c =0; c<n; ++c) {
			// check the colSet does not have this column
			// check the positive and negative diagonal does not have value
			if(!colSet.contains(c) && !postiveDig.contains(r+c)  && !negativeDig.contains(r-c)) {
				// add to colset and other set
				colSet.add(c);
				postiveDig.add(r+c);
				negativeDig.add(r-c);
				// update boards for the Queen
				boards[r][c]='Q';
				solveWithBackTrackingDFC(n, r+1, result, boards);
				// remove added value for clean up
				colSet.remove(c);
				postiveDig.remove(r+c);
				negativeDig.remove(r-c);
				boards[r][c]='.';
			}
		}
	}
	
	public static void main(String[] args) {
		NQueens nQueens= new NQueens();
		System.out.println(nQueens.solveNQueens(4));
	}

}
