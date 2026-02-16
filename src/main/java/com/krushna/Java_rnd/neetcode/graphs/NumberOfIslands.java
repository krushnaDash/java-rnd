package com.krushna.Java_rnd.neetcode.graphs;

/**
https://neetcode.io/problems/count-number-of-islands/question
Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.
An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water. 
You may assume water is surrounding the grid (i.e., all the edges are water). 
 */
public class NumberOfIslands {

		public int numIslands(char[][] grid) {
			int islandCount=0;
			for(int i=0; i< grid.length; ++i) {
				for(int j=0; j< grid[i].length; ++j) {
					if(grid[i][j] == '1') {
						// make it 0 to mark this as visited and do DFS to check near by lands and mark them 0
						islandCount++;
						dfs(grid, i, j);
					}
					
				}
			}
			return islandCount;
	    }
		public void dfs(char[][] grid, int r, int c) {
			// check out of bound and 0
			if(r<0 || c<0 || r>= grid.length || c>= grid[0].length || grid[r][c] =='0') {
				return;
			}
			grid[r][c]='0';
			dfs(grid, r-1,c);
			dfs(grid, r+1,c);
			dfs(grid, r,c+1);
			dfs(grid, r,c-1);
		}
		
	}


