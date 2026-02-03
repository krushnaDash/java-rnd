package com.krushna.Java_rnd.neetcode.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-search/description/
 * 
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 */
public class WordSearch {
	
	Set<String> visitedPath= new HashSet<String>();
	
	public boolean exist(char[][] board, String word) {
		// run the BFS for each char
		
		for(int i=0; i < board.length; ++i) {
			for(int j=0; j< board[i].length; ++j) {
				if(existBacktrackingDFS(board, word, i, j,0)) {
					return true;
				}
			}
		}
        return false;
    }
	
	/**
	 * This will run for each char
	 * If i reach to word length return true
	 */
	public boolean existBacktrackingDFS(char[][] board, String word, int row, int col, int i) {
		// base case when i == word.length
		if(i== word.length()) {
			return true;
		}
		// we should process the same path again, so have set to check
		// invalid case for return false
		if(row <0 || col<0 || row >=board.length || col>= board[0].length || visitedPath.contains(row+","+col) 
				|| word.charAt(i)!= board[row][col] ) {
			return false;
		}
		visitedPath.add(row+","+col);
		
		boolean res= existBacktrackingDFS(board, word, row+1, col, i+1)
				|| existBacktrackingDFS(board, word, row-1, col, i+1)
				|| existBacktrackingDFS(board, word, row, col-1, i+1)
				|| existBacktrackingDFS(board, word, row, col+1, i+1);
		
		// we need to do clean up of the set, since it is the global variable
		visitedPath.remove(row+","+col);
		
		return res;
		
	}

	/**
	 * The fundamental issue with fixing the BFS approach is that BFS explores
	 * level-by-level breadth-first, but this problem requires finding a single
	 * continuous path. So lets not try to solve it by BFS
	 */
	
	public boolean existBackTrackingBFS(char[][] board, String word, int rowStart, int colStart) {
		int boardRowSize = board.length;
		int boardColSize = board[0].length;

		Set<String> processPath = new HashSet<String>();
		Queue<int[]> nodes = new LinkedList<int[]>();
		nodes.add(new int[] { rowStart, colStart });
		int i = 0;

		while (!nodes.isEmpty() && i < word.length()) {
			int queueSize = nodes.size();
			boolean matchFound = false;
			for (int j = 0; j < queueSize; ++j) {
				int[] pos = nodes.poll();
				int row = pos[0];
				int col = pos[1];
				char c = board[row][col];
				// path matched
				if (c == word.charAt(i) && !processPath.contains(row+","+col)) {
					processPath.add(row+","+col);
					matchFound = true;
					// store all the possible path from the current node to the queue
					if (row - 1 >= 0 && !processPath.contains(row - 1 +","+ col )) {
						nodes.add(new int[] { row - 1, col });
					}
					if (col - 1 >= 0 && !processPath.contains(row  +","+ (col-1))) {
						nodes.add(new int[] { row, col - 1 });
					}
					if (col + 1 < boardColSize && !processPath.contains(row+","+(col + 1) )) {
						nodes.add(new int[] { row, col + 1 });
					}
					if (row + 1 < boardRowSize && !processPath.contains(row + 1 +","+ col )) {
						nodes.add(new int[] { row + 1, col });
					}
				}

			}
			if (matchFound) {
				++i;
			} else {
				return false;
			}
		}
		if(i == word.length())
			return true;
		else
			return false;

	}
	
	public static void main(String[] args) {
		WordSearch search= new WordSearch();
		// board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
		char[][] board= {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		char[][] board2= {{'A','B'},{'C','D'}};
		System.out.println(search.exist(board, "ABCB"));
		System.out.println(search.exist(board2, "ABCD"));
	}
	
	

}
