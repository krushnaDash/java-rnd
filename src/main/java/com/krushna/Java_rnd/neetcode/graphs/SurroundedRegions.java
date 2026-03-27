package com.krushna.Java_rnd.neetcode.graphs;

import java.util.Arrays;

/**
 * You are given an m x n matrix board containing letters 'X' and 'O', capture
 * regions that are surrounded:
 * 
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell. Surround: A region is
 * surrounded if none of the 'O' cells in that region are on the edge of the
 * board. Such regions are completely enclosed by 'X' cells.
 * 
 * To capture a surrounded region, replace all 'O's with 'X's in-place within
 * the original board. You do not need to return anything.
 */

public class SurroundedRegions {
	
	public void solve(char[][] board) {
        // our logic is reverse the statement capture everything except un-surrounded regions
		// So Take only the boarder cell and capture by marking them T, if it is 0,
		// As these are not surrounded
		
		for(int r=0; r< board.length; ++r) {
			for(int c=0; c< board[0].length; ++c) {
				if (board[r][c] == 'O' && r == 0 || 
						r == board.length - 1 || c == 0 
						|| c == board[0].length - 1) { // it is boarder cell
					DFS(r, c, board);// do DFS to make them un-capture with T
				}
			}
		}
		// Phase 2 mark the reaming O to X and T to O
		
		for(int r=0; r< board.length; ++r) {
			for(int c=0; c< board[0].length; ++c) {
				if (board[r][c] == 'O')
					board[r][c]='X';
				
				if (board[r][c] == 'T') // change T  to O
					board[r][c]='O';
				}
			}
				
				
		}
		
		
    
	public void DFS(int r, int c, char[][] board) {
		// out of bound or it is X
		
		if(r<0 || c< 0 || r >= board.length || c >= board[0].length || board[r][c]=='X' || board[r][c]=='T')
			return;
		
		board[r][c]='T';
		DFS(r+1, c, board);
		DFS(r-1, c, board);
		DFS(r, c+1, board);
		DFS(r, c-1, board);
	}
	
	public static void main(String[] args) {
		char[][] board= new char [][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		//new SurroundedRegions().solve(board);
		for(char[] bc: board)
			System.out.println(Arrays.toString(bc));
		
		
		char[][] board2= new char [][] {{'O','O'},{'O','O'}};
		new SurroundedRegions().solve(board2);
		
		
	}

}
